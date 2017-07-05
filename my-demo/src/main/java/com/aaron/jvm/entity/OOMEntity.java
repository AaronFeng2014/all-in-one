package com.aaron.jvm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-17
 */
@Setter
@Getter
@ToString
public class OOMEntity
{
    private String name;

    private Map<String, Integer> map = new HashMap<>();

    private List<String> list = new ArrayList<>();
}
