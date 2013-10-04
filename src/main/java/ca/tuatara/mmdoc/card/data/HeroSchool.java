package ca.tuatara.mmdoc.card.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class HeroSchool {
    @JacksonXmlProperty(isAttribute = true)
    private MagicSchool name;
}
