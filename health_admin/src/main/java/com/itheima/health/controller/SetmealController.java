package com.itheima.health.controller;

import com.heima.file.service.FileStorageService;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.R;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.model.pojos.Setmeal;
import com.itheima.health.service.impl.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 功能描述: 套餐模块分页查询
     *
     * @return : com.itheima.health.common.ResultPageData
     */
    @PostMapping("/findPage")
    public ResultPageData findSetmealPage(@RequestBody PageParam pageParam) {
        return setmealService.findPage(pageParam);
    }


    /**
     * 功能描述: 图片上传
     *
     * @return : com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("upload")
    public R upload(MultipartFile imgFile) {

        if (imgFile == null || imgFile.getSize() == 0) {
            return R.error(MessageConstant.PIC_UPLOAD_FAIL);
        }
        // 获取文件前端传过来的文件名
        String originalFilename = imgFile.getOriginalFilename();
        // 获取文件的后缀名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String prefix = String.valueOf(System.currentTimeMillis());
        //文件变为字节流
        InputStream inputStream = null;
        try {
            inputStream = imgFile.getInputStream();
            String url = fileStorageService.uploadImgFile("", prefix + substring, inputStream);
            return R.success(MessageConstant.PIC_UPLOAD_SUCCESS, url);
        } catch (IOException e) {
            return R.error(MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 功能描述: 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     * @return : com.itheima.health.common.R
     */
    @PostMapping("/add")
    public R addSetmeal(@RequestBody Setmeal setmeal, @RequestParam Integer[] checkgroupIds) {
        try {
            setmealService.addSetmeal(setmeal, checkgroupIds);
            return R.success(MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

}
