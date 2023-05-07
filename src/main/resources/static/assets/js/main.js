(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();


    // Initiate the wowjs
    new WOW().init();


    // Sticky Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 45) {
            $('.navbar').addClass('sticky-top shadow-sm');
        } else {
            $('.navbar').removeClass('sticky-top shadow-sm');
        }
    });


    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";

    $(window).on("load resize", function () {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
                function () {
                    const $this = $(this);
                    $this.addClass(showClass);
                    $this.find($dropdownToggle).attr("aria-expanded", "true");
                    $this.find($dropdownMenu).addClass(showClass);
                },
                function () {
                    const $this = $(this);
                    $this.removeClass(showClass);
                    $this.find($dropdownToggle).attr("aria-expanded", "false");
                    $this.find($dropdownMenu).removeClass(showClass);
                }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    function prepareForClick() {
        const cartButtons = document.querySelectorAll('.cart-button');

        cartButtons.forEach(button => {
            button.addEventListener('click', cartClick);
        });

        function cartClick() {
            let button = this;
            let urunId = button.id;

            addToCart(urunId);
        }


        const checkoutButton = document.querySelectorAll('.checkout-ok-button');
        checkoutButton.forEach(button => {
            button.addEventListener('click', checkoutClick);
        });

        function checkoutClick() {
            checkout();
        }


        const statusCombos = document.querySelectorAll('select[id^="active-order-"]');
        statusCombos.forEach(button => {
            button.addEventListener('change', statusComboOnChange);
        });

        function statusComboOnChange() {
            let orderId = $(this).attr('id').replace("active-order-", "");
            let status = $(this).val();

            let origin = window.location.origin;
            window.location.href = origin + "/admin/orders?orderId=" + orderId + "&status=" + status;
        }

        const urunRemoves = document.querySelectorAll('.urun-remove-i');
        urunRemoves.forEach(button => {
            button.addEventListener('click', removeUrunClick);
        });

        function removeUrunClick(e) {
            let urunId = $(this).attr('id');
            removeUrun(e, urunId);
        }

        const urunUpdates = document.querySelectorAll('.urun-update-i');
        urunUpdates.forEach(button => {
            button.addEventListener('click', updateUrunClick);
        });

        function updateUrunClick(e) {
            let urunId = $(this).attr('id');

            openModalWithUrun(e, urunId);
        }
    }

    function openModalWithUrun(e, urunId) {
        $.ajax({
            url: '/admin/products/' + urunId,
            type: 'post',
            dataType: 'json',
            data: {},
            success: function (data) {
                if (data && data.success && data.success === true) {
                    openAndFillModal(data.urun);
                } else {
                    alert("Beklenmedik Bir Hata Oluştu");
                }
            },
            error: function (request, status, error) {
                alert("Beklenmedik Bir Hata Oluştu");
            }
        })
    }

    function openAndFillModal(urun) {
        $('#modal-urun').modal('toggle');

        let urunId = urun.id;
        let image = urun.image;
        let name = urun.name;
        let price = urun.price;
        let category = urun.category;
        let description = urun.description;

        $("#urunId").val(urunId);
        $("#image").val(image);
        $("#name").val(name);
        $("#price").val(price);
        $("#category").val(category);
        $("#description").val(description);
    }

    function checkout() {

        const sessionId = $("input#sessionId").val()
        const name = $("input#name").val()
        const email = $("input#email").val()
        const note = $("input#note").val()

        $.ajax({
            url: '/checkout',
            type: 'post',
            dataType: 'json',
            data: {"name": name, "email": email, "note": note, "sessionId": sessionId},
            success: function (data) {
                if (data && data.success && data.success === true) {

                    alert("Sipariş başarılı.");

                    let origin = window.location.origin;
                    window.location.href = origin + "/orders?orderCode=" + data.orderCode;
                } else {
                    alert("Beklenmedik Bir Hata Oluştu");
                }
            },
            error: function (request, status, error) {
                alert("Beklenmedik Bir Hata Oluştu");
            }
        })
    }

    function addToCart(urunId) {

        const sessionId = $("input#sessionId").val()

        $.ajax({
            url: '/add-to-cart',
            type: 'post',
            dataType: 'json',
            data: {"urunId": urunId, "sessionId": sessionId},
            success: function (data) {
                if (data && data.success && data.success === true) {
                    $('.cart-basket').text(data.cartsSize);
                    alert("Sepete Eklendi");
                } else {
                    alert("Beklenmedik Bir Hata Oluştu");
                }
            },
            error: function (request, status, error) {
                alert("Beklenmedik Bir Hata Oluştu");
            }
        })
    }

// Modal Video
    $(document).ready(function () {

        prepareForClick();

        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })

        $('.yeni-urun-ekle').click(function (e) {
            $('#modal-urun').modal('toggle');

            e.preventDefault();
            return false;
        });

        $('.close-modal-urun').click(function (e) {
            $('#modal-urun').modal('hide');

            e.preventDefault();
            return false;
        });

        $('.cancel-modal-urun').click(function (e) {
            $('#modal-urun').modal('hide');

            e.preventDefault();
            return false;
        });

        $('.modal-urun-save-button').click(function (e) {
            saveUrun(e);
            return false;
        });

    });

    function saveUrun(e) {
        e.preventDefault();

        const urunId = $("#urunId").val()
        const image = $("#image").val()
        const name = $("#name").val()
        const price = $("#price").val()
        const category = $("#category").val()
        const description = $("#description").val()

        $.ajax({
            url: '/admin/products/save',
            type: 'post',
            dataType: 'json',
            data: {
                "urunId": urunId,
                "image": image,
                "name": name,
                "price": price,
                "category": category,
                "description": description
            },
            success: function (data) {
                if (data && data.success && data.success === true) {
                    let origin = window.location.origin;
                    window.location.href = origin + "/admin/products";
                } else {
                    alert("Beklenmedik Bir Hata Oluştu");
                }
            },
            error: function (request, status, error) {
                alert("Beklenmedik Bir Hata Oluştu");
            }
        })
    }

    function removeUrun(e, urunId) {
        e.preventDefault();

        $.ajax({
            url: '/admin/products/remove',
            type: 'post',
            dataType: 'json',
            data: {"urunId": urunId},
            success: function (data) {
                if (data && data.success && data.success === true) {
                    let origin = window.location.origin;
                    window.location.href = origin + "/admin/products";
                } else {
                    alert("Beklenmedik Bir Hata Oluştu");
                }
            },
            error: function (request, status, error) {
                alert("Beklenmedik Bir Hata Oluştu");
            }
        })
    }

})(jQuery);

