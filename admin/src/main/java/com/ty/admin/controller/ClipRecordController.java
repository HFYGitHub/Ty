package com.ty.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ty.admin.entity.ClipRecord;
import com.ty.admin.mapper.ClipRecordMapper;
import com.ty.admin.web.dto.ClipRecordAddRequest;
import com.ty.admin.web.dto.PageRequest;
import com.ty.admin.web.vo.PageResult;
import com.ty.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class ClipRecordController {

    private final ClipRecordMapper clipRecordMapper;

    @PostMapping
    public ClipRecord add(@RequestBody ClipRecordAddRequest request) {
        long start = System.currentTimeMillis();
        try {
            if (request == null || request.getContent() == null || request.getContent().trim().isEmpty()) {
                throw new CommonException(400, "内容不能为空");
            }

            ClipRecord record = new ClipRecord();
            record.setContent(request.getContent().trim());
            record.setCreateTime(LocalDateTime.now());
            clipRecordMapper.insert(record);
            return record;
        } finally {
            log.info("POST /api/records cost {} ms", System.currentTimeMillis() - start);
        }
    }

    @GetMapping
    public PageResult<ClipRecord> list(PageRequest request) {
        long start = System.currentTimeMillis();
        try {
            long safePage = request.getSafePage();
            long safePageSize = request.getSafePageSize();
            Page<ClipRecord> recordPage = new Page<>(safePage, safePageSize);
            Page<ClipRecord> resultPage = clipRecordMapper.selectPage(recordPage, new LambdaQueryWrapper<ClipRecord>()
                    .orderByDesc(ClipRecord::getCreateTime)
                    .orderByDesc(ClipRecord::getId));
            log.info("GET /api/records page {} pageSize {} returned {} total {}",
                    safePage, safePageSize, resultPage.getRecords().size(), resultPage.getTotal());
            return new PageResult<>(
                    resultPage.getCurrent(),
                    resultPage.getSize(),
                    resultPage.getTotal(),
                    resultPage.getPages(),
                    resultPage.getRecords());
        } finally {
            log.info("GET /api/records cost {} ms", System.currentTimeMillis() - start);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        try {
            clipRecordMapper.deleteById(id);
        } finally {
            log.info("DELETE /api/records/{} cost {} ms", id, System.currentTimeMillis() - start);
        }
    }
}
