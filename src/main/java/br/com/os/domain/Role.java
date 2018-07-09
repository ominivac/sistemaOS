package br.com.os.domain;

public enum Role {
	
		ADMINISTRADOR("A"), USUARIO("U");
	
		private String descricao;
		
		Role(String desc) {
			this.descricao = desc;
		}
		
		
		public String getDescricao() {
			return descricao;
		}
		
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		//para o converter nativo jsf
		public Role[] getRoles() {
			return Role.values();
		}
		
		
		@Override
		public String toString() {
			return this.descricao;
		}
	
		
		
	

}
