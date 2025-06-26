package com.github.benxincaomu.trydemo.web;

import com.github.benxincaomu.notry.utils.Asserts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("try")
public class TryController {
    @GetMapping("getOne")
    public TryEntity getEntity(){
        TryEntity entity = new TryEntity();
        entity.setName("张三");
        return entity;
    }

    @GetMapping("getList")
    public Page<TryEntity> getList(){
        TryEntity entity = new TryEntity();
        entity.setName("张三");
        List<TryEntity> entities = new ArrayList<>();
        entities.add(entity);
        Page<TryEntity> page = new Page<>();
        page.setRecords(entities);
        return page;
    }

    @GetMapping("exception")
    public TryEntity exception(){
        Asserts.error();
        return null;
    }

    @GetMapping("getInt")
    public int getInt(){
        return 1;
    }

}
