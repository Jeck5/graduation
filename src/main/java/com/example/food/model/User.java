package com.example.food.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@NamedQueries({
        @NamedQuery(name = User.GET_BY_ID, query = "SELECT u FROM User u WHERE u.id=?1"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = User.VOTES, query = "SELECT v FROM Vote v WHERE v.user.id = ?1 ORDER BY v.date, v.time DESC"),
})
@NamedEntityGraph(name = User.GRAPH_WITH_VOTE_HISTORY_AND_ROLES, includeAllAttributes = true)
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    public static final String GRAPH_WITH_VOTE_HISTORY_AND_ROLES = "User.withVoteHistory";

    public static final String GET_BY_ID = "User.getById"; //TODO delete all unnecessary
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String VOTES = "User.getLastVote";

    public static final int START_SEQ = 100000;

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
