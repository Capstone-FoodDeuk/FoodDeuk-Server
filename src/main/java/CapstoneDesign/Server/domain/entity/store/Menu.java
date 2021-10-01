package CapstoneDesign.Server.domain.entity.store;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;
    private Long price;

    @Embedded
    private ImageFile image;
    private Boolean soldOut;

    public Menu(Store store, String name, Long price, Boolean soldOut) {
        this.store = store;
        this.name = name;
        this.price = price;
        this.soldOut = soldOut;
        store.getMenuList().add(this);
    }

    /**
     * set image
     */
    public void addImageFile(ImageFile imageFile) {
        this.image = imageFile;
    }
}
