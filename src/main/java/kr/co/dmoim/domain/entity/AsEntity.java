package kr.co.dmoim.domain.entity;

import kr.co.dmoim.domain.code.AsStat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicUpdate
@Table(name = "as_board")
public class AsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "as_seq")
    private Long seqAs;

    @Column(length = 100, nullable = false, name = "as_title")
    private String titleAs;

    @Column(columnDefinition = "TEXT", nullable = false, name = "as_contents")
    private String contentsAs;

    @Column(length = 20, nullable = false, name = "as_tel_no")
    private String noTelAs;

    @Column(nullable = false, name = "as_latitude")
    private BigDecimal latitudeAs;

    @Column(nullable = false, name = "as_longitude")
    private BigDecimal longitudeAs;

    @Column(nullable = false, name= "as_location")
    private String locationAs;

    @Column(nullable = true, name = "as_location_detail")
    private String dtlLocationAs;

    @Column(length = 100, nullable = false, name = "as_password")
    private String passwordAs;

    @Column(length = 20, name = "as_stat")
    @Enumerated(EnumType.STRING)
    private AsStat statAs;

    @Column(columnDefinition = "TEXT", name = "as_comment")
    private String commentAs;

    @Column(length = 30, nullable = true, name = "attach_id")
    private String idAttach;

    @Column(length = 20, nullable = false, name = "register", updatable = false)
    private String writerAs;

    @Column(nullable = true, name = "regdt", updatable = false)
    private LocalDateTime regdtAs;

    @Column(length = 20, nullable = true, name = "modifier")
    private String modifierAs;

    @Column(nullable = true, name = "moddt")
    private LocalDateTime moddtAs;

    @Column(length = 1, nullable = false, name = "del_yn")
    private String ynDel;

    @Builder
    public AsEntity(Long seqAs, String titleAs, String contentsAs, String noTelAs, BigDecimal latitudeAs, BigDecimal longitudeAs,
                    String locationAs, String dtlLocationAs, String passwordAs, AsStat statAs, String commentAs,
                    String idAttach, String writerAs, LocalDateTime regdtAs, String modifierAs, LocalDateTime moddtAs,
                    String ynDel) {
        this.seqAs = seqAs;
        this.titleAs = titleAs;
        this.contentsAs = contentsAs;
        this.noTelAs = noTelAs;
        this.latitudeAs = latitudeAs;
        this.longitudeAs = longitudeAs;
        this.locationAs = locationAs;
        this.dtlLocationAs = dtlLocationAs;
        this.passwordAs = passwordAs;
        this.statAs = statAs;
        this.commentAs = commentAs;
        this.idAttach = idAttach;
        this.writerAs = writerAs;
        this.regdtAs = regdtAs;
        this.modifierAs = modifierAs;
        this.moddtAs = moddtAs;
        this.ynDel = ynDel;
    }
}
