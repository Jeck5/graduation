package com.example.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class AbstractBaseEntity {

    @Id
    @SequenceGenerator(name = "global_db_seq", sequenceName = "global_db_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_db_seq")
    @Getter
    @Setter
    private Integer key;

    @JsonIgnore
    public boolean isNew(){
        return this.key == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return key != null ? key.equals(that.key) : that.key == null;

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
