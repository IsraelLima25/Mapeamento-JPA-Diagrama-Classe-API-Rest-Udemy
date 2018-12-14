package com.curso.mc.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.Endereco;
import com.curso.mc.domain.enuns.TipoCliente;
import com.curso.mc.dto.ClienteDTO;
import com.curso.mc.dto.ClienteNewDto;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.repositories.EnderecoRepository;
import com.curso.mc.service.exceptions.DataIntegrityException;
import com.curso.mc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository repoEnd;

	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como
	 * objetivo evitar as exceptions do tipo "NullPointException", guardando em si
	 * um contexto da busca e não somente o valor retornado na query.
	 */
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();

	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		repoEnd.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente oldObj) {
		Cliente newObj = find(oldObj.getId());
		updateData(newObj, oldObj);
		return repo.save(newObj);

	}

	public void delete(Integer id) {
		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException ex) {

			throw new DataIntegrityException(
					"Regra de integridade violada em " + id + " Tipo " + Cliente.class.getName());
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDto objDto) {

		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));

		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cid, cli);

		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}

		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}

		return cli;

	}

	private void updateData(Cliente newObj, Cliente oldObj) {
		newObj.setNome(oldObj.getNome());
		newObj.setEmail(oldObj.getEmail());

	}

}
