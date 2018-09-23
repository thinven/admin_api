package com.thinven.boot.domain.wrapper;

import javax.servlet.http.HttpServletRequest;

import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.CheckService;
import com.thinven.boot.support.domain.entity.service.DeleteService;
import com.thinven.boot.support.domain.entity.service.InfoService;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.SearchService;
import com.thinven.boot.support.domain.entity.service.UpdateService;

/**
 * 서포트팩토리에서 옮김(트랜잭션 범위안에 들기위해).
 * 
 * @author needthink
 * 
 * @param <G>
 */
public interface WrapperService<G> {

	public static final String WHITE_SERVER = "thinven.com";

	public Message<G> list(ListService<G> listService, G entity, HttpServletRequest request);

	public Message<G> search(SearchService<G> listService, G entity, HttpServletRequest request);

	public Message<G> add(AddService<G> addService, G entity, HttpServletRequest request);

	public Message<G> info(InfoService<G> infoService, G entity, HttpServletRequest request);

	public Message<G> update(UpdateService<G> updateService, G entity, HttpServletRequest request);

	public Message<G> delete(DeleteService<G> deleteService, G entity, HttpServletRequest request);

	public Message<G> check(CheckService<G> checkService, G entity, HttpServletRequest request);

}
