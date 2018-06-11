package com.graduation.votingsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(name = User.GRAPH_WITH_VOTE_HISTORY_AND_ROLES, includeAllAttributes = true)
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    public static final String GRAPH_WITH_VOTE_HISTORY_AND_ROLES = "User.withVoteHistory";

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean enabled = true;

//    @Column
//    private Date registered = new Date();

//    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;
}
