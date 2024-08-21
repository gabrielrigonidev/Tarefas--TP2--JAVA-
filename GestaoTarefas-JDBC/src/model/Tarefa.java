package model;

public class Tarefa {
   private int id;
   private String titulo;
   private String descricao;
   private String categoria;
   private boolean status = false;

   public Tarefa(int id, String titulo, String descricao, String categoria, boolean status) {
      this.id = id;
      this.titulo = titulo;
      this.descricao = descricao;
      this.categoria = categoria;
      this.status = status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }
   public void setaStatus() {
      this.status = true;
   }
   public void marcarComoConcluida(){
      if(!getStatus()){
         setaStatus();
      } else {
         System.out.println("Tarefa setada como Concluida!");
      }
   }

   public void setId(int id) {
      this.id = id;
   }
   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }
   public void setCategoria(String categoria) {
      this.categoria = categoria;
   }

   public int getId() {
      return this.id;
   }
   public String getTitulo() {
      return this.titulo;
   }
   public String getDescricao() {
      return this.descricao;
   }
   public boolean getStatus() {
      return this.status;
   }
   public String getCategoria() {
      return this.categoria;
   }
   @Override
   public String toString() {
      return "Tarefa{" +
              "id=" + getId() +
              ", titulo='" + getTitulo() + '\'' +
              ", descricao='" + getDescricao() + '\'' +
              ", status='" + getStatus() + '\'' +
              ", categoria='" + getCategoria() + '\'' +
              '}';
   }

}
