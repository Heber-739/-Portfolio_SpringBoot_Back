package com.portfolio.Service;

import com.portfolio.Entity.Image;
import com.portfolio.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {

    @Autowired
    ImageRepository irepository;

    public Image findById(int id) {
        return irepository.findById(id).orElse(null);
    }

    public Image findByName(String name) {
        return irepository.findByName(name).orElse(null);
    }

    public void save(Image img) {
        irepository.save(img);
    }

    public void delete(int id) {
        irepository.deleteById(id);
    }

}
