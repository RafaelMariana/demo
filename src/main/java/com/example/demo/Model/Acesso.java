package com.example.demo.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "ip", nullable = false, length = 14)
    private String ip;

    @Column(name = "dataehora", nullable = false, length = 14)
    private String dataehora;  
    
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
   


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDataehora() {
        return this.dataehora;
    }

    public void setDataehora(String dataehora) {
        this.dataehora = dataehora;
    }

    
}
