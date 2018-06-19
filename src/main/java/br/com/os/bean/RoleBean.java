package br.com.os.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.com.os.domain.Role;

@ManagedBean
@ApplicationScoped
public class RoleBean {
	
	private List<Role> roles;
	private Role role;
	
	@PostConstruct
	public void init() {
		roles = new ArrayList<Role>();
	}
	
	
	public SelectItem[] getRoles() {
	    SelectItem[] items = new SelectItem[Role.values().length];
	    int i = 0;
	    for(Role r: Role.values()) {
	      items[i++] = new SelectItem(r, r.getDescricao() );
	    }
	    return items;
	  }
	
	
	public Role[] getAll() {
		return Role.values();
	}
	
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}

