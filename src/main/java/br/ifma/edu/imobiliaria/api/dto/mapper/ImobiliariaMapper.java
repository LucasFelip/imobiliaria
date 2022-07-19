package br.ifma.edu.imobiliaria.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.ifma.edu.imobiliaria.api.dto.request.ImobiliariaRequest;
import br.ifma.edu.imobiliaria.api.dto.response.ImobiliariaResponse;
import br.ifma.edu.imobiliaria.domain.model.Imobiliaria;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImobiliariaMapper {
    private final ModelMapper modelMapper;

    public Imobiliaria toEntity(ImobiliariaRequest imobiliariaRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(imobiliariaRequest, Imobiliaria.class);
    }

    public ImobiliariaResponse toResponse(Imobiliaria imobiliaria) {
        return modelMapper.map(imobiliaria, ImobiliariaResponse.class);
    }

    public List<Imobiliaria> toEntityList(List<ImobiliariaRequest> listaImobiliariaRequest) {
        return listaImobiliariaRequest.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<ImobiliariaResponse> toResponseList(List<Imobiliaria> listaImovel) {
        return listaImovel.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
