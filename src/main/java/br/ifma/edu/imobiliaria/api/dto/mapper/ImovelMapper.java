package br.ifma.edu.imobiliaria.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.ifma.edu.imobiliaria.api.dto.request.ImovelRequest;
import br.ifma.edu.imobiliaria.api.dto.response.ImovelResponse;
import br.ifma.edu.imobiliaria.domain.model.Imovel;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ImovelMapper {
    private final ModelMapper modelMapper;

    public Imovel toEntity(ImovelRequest imovelRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(imovelRequest, Imovel.class);
    }

    public ImovelResponse toResponse(Imovel imovel) {
        return modelMapper.map(imovel, ImovelResponse.class);
    }

    public List<Imovel> toEntityList(List<ImovelRequest> listaImovelRequest) {
        return listaImovelRequest.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<ImovelResponse> toResponseList(List<Imovel> listaImovel) {
        return listaImovel.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
