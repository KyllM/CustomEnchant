package fr.kyll01.customenchant;

import java.util.List;

public record EnchantsType(String name, String displayname, List<String> lore, List<ToolsType> tools) {}
