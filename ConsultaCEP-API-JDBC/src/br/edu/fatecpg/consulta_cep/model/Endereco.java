package br.edu.fatecpg.consulta_cep.model;

import com.google.gson.annotations.SerializedName;

public class Endereco {
   private int id;

   private String cep;

   @SerializedName("logradouro")
   private String rua;

   @SerializedName("bairro")
   private String bairro;

   @SerializedName("localidade")
   private String cidade;

   @SerializedName("uf")
   private String estado;

   public String getCep() {
      return cep;
   }
   public String getRua() {
      return rua;
   }
   public String getBairro() {
      return bairro;
   }
   public String getCidade() {
      return cidade;
   }
   public String getUF() {
      return estado;
   }

   public void setId(int id) {
      this.id = id;
   }
   public void setCep(String cep) {
      this.cep = cep;
   }
   public void setRua(String rua) {
      this.rua = rua;
   }
   public void setBairro(String bairro) {
      this.bairro = bairro;
   }
   public void setCidade(String cidade) {
      this.cidade = cidade;
   }
   public void setEstado(String estado) {
      this.estado = estado;
   }

   @Override
   public String toString() {
      return "Endereco {" +
              "ID=" + id +
              ", CEP='" + cep + '\'' +
              ", Rua='" + rua + '\'' +
              ", Bairro='" + bairro + '\'' +
              ", Cidade='" + cidade + '\'' +
              ", Estado='" + estado + '\'' +
              '}';
   }
}