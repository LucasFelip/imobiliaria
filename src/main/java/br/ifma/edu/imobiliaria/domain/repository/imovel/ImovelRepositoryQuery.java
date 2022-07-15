package br.ifma.edu.imobiliaria.domain.repository.imovel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.filter.ImovelFilter;

public interface ImovelRepositoryQuery {
    Page<Imovel> filtrar(ImovelFilter filtro, Pageable pageable);
}
