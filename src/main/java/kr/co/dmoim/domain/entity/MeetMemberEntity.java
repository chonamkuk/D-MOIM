package kr.co.dmoim.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicUpdate
@Table(name = "meeting_member")
public class MeetMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long seqMember;

    @ManyToOne
    @JoinColumn(name = "meet_seq")
    private MeetEntity meetEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @Column(length = 100, name = "member_name")
    private String nameMember;

    @Column(length = 100, name ="member_email")
    private String emailMember;

    @Builder
    public MeetMemberEntity(Long seqMember, MeetEntity meetEntity, AccountEntity accountEntity, String nameMember, String emailMember) {
        if(meetEntity.getMeetMembers() == null) {
            meetEntity.setMeetMembers(new ArrayList<MeetMemberEntity>());
        }
        this.seqMember = seqMember;
        this.meetEntity = meetEntity;
        this.accountEntity = accountEntity;
        this.nameMember = nameMember;
        this.emailMember = emailMember;
    }
}
