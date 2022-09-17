package com.ssl.notebase.mall.learn.impl;

import com.ssl.notebase.mall.learn.mbg.model.PmsBrand;

import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/17 17:54
 * @Describe:
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
