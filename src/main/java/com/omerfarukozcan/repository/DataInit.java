package com.omerfarukozcan.repository;

import com.omerfarukozcan.entity.UrunModel;
import com.omerfarukozcan.entity.UserModel;
import com.omerfarukozcan.repository.UrunRepository;
import com.omerfarukozcan.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static com.omerfarukozcan.util.CommonUtil.encodePass;

@Slf4j
@Component
public class DataInit {

    private final UserRepository userRepository;
    private final UrunRepository urunRepository;

    public DataInit(UserRepository userRepository, UrunRepository urunRepository) {
        this.userRepository = userRepository;
        this.urunRepository = urunRepository;
    }

    @PostConstruct
    public void init() {

        try {
            final UserModel user = new UserModel();
            user.setId(13L);
            user.setEmail("admin@restoran-otomasyon.com");
            user.setPassword(encodePass("Admin0*"));
            user.setLang("tr");
            user.setEnabled(true);

            userRepository.save(user);

            UrunModel urun = new UrunModel();
            urun.setId(13L);
            urun.setImage("menu-1.jpg");
            urun.setPrice(new BigDecimal("170"));
            urun.setName("Serpme Kahvaltı 1");
            urun.setCategory("Kahvaltı");
            urun.setDescription("Peynir çeşitlerinden zeytin çeşitlerine tarlamızdan sofranıza.");

            urunRepository.save(urun);
        } catch (Exception e) {
            log.error("db init failed");
        }
    }

}
