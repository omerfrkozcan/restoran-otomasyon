package com.omerfarukozcan.service;

import com.omerfarukozcan.entity.UrunModel;
import com.omerfarukozcan.model.UrunItem;
import com.omerfarukozcan.model.UrunSaveRequest;
import com.omerfarukozcan.model.UrunSaveResponse;
import com.omerfarukozcan.repository.UrunRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.omerfarukozcan.util.CommonUtil.izNotNull;

@Slf4j
@Service
public class UrunService {

    private final UrunRepository repository;

    public UrunService(UrunRepository repository) {
        this.repository = repository;
    }

    public List<UrunModel> findAll() {
        return repository.findAllByOrderByUpdatedAtDesc();
    }

    public UrunSaveResponse save(UrunSaveRequest request) {

        UrunModel urun = izNotNull(request.getUrunId()) ? repository.getReferenceById(request.getUrunId()) : new UrunModel();
        urun.setImage(request.getImage());
        urun.setName(request.getName());
        urun.setPrice(request.getPrice());
        urun.setCategory(request.getCategory());
        urun.setDescription(request.getDescription());
        repository.save(urun);

        return new UrunSaveResponse(true);
    }

    public UrunSaveResponse remove(UrunSaveRequest request) {

        repository.deleteById(request.getUrunId());

        return new UrunSaveResponse(true);
    }

    public UrunSaveResponse get(Long urunId) {
        final UrunSaveResponse response = new UrunSaveResponse(true);
        response.setUrun(toItem(repository.getReferenceById(urunId)));

        return response;
    }

    private UrunItem toItem(UrunModel urun) {
        final UrunItem item = new UrunItem();
        item.setUrunId(urun.getId());
        item.setId(urun.getId());
        item.setImage(urun.getImage());
        item.setName(urun.getName());
        item.setPrice(urun.getPrice());
        item.setCategory(urun.getCategory());
        item.setDescription(urun.getDescription());

        return item;
    }

    public List<UrunModel> findAllByCategory(String category) {
        return repository.findAllByCategoryOrderByUpdatedAtDesc(category);
    }
}
