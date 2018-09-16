package com.thinven.boot.domain.entity.memberset.member.service;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.CheckService;
import com.thinven.boot.support.domain.entity.service.InfoService;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.MemberInfoService;
import com.thinven.boot.support.domain.entity.service.UpdateService;

public interface MemberService
		extends MemberInfoService, ListService<Member>, AddService<Member>, InfoService<Member>, CheckService<Member>, UpdateService<Member> {

	public static final long GUEST_SEQ = 1;
	public static final String GUEST_UID = "guest_uid";
	public static final long FOREVER_DAYS = 365000;

	public static final long GOODBYE_DAYS = 7;

	public final static String PICK_RECEIVER = "pickreceiver";
	public final static String GOODBYE = "goodbye";
	public final static String GOODBYE_CANCEL = "goodbyecancel";
	public final static String BACK_OFFICE = "back";

	public static final String JOIN_INFO = "joininfo";
	public static final String EDIT_INFO = "editinfo";
	public static final String FIND_ID = "find_id";
	public static final String FIND_PW = "find_pw";
	public static final String EDIT_PASSWORD = "edit_password";

	public Message<Member> isMember(Message<Member> msg);

	public Message<Member> equalsPW(Message<Member> msg);

	public Member infoByRk(Member member);

}
