package br.ifma.edu.imobiliaria.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.ifma.edu.imobiliaria.api.dto.request.UsuarioRequest;
import br.ifma.edu.imobiliaria.api.dto.response.UsuarioResponse;
import br.ifma.edu.imobiliaria.domain.model.Usuario;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioMapper {
    private final ModelMapper modelMapper;

    public Usuario toEntity(UsuarioRequest usuarioRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(usuarioRequest, Usuario.class);
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    public List<Usuario> toEntityList(List<UsuarioRequest> listaUsuarioRequest) {
        return listaUsuarioRequest.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponse> toResponseList(List<Usuario> listaUsuario) {
        return listaUsuario.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
