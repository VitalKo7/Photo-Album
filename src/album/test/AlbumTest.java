package album.test;

import album.dao.Album;
import album.dao.AlbumImpl;
import album.model.Photo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {
    Album album;
    Photo[] photo;
    LocalDateTime now = LocalDateTime.now();
    Comparator<Photo> comparator = (p1, p2) -> {
        int res = Integer.compare(p1.getPhotoId(), p2.getPhotoId());
        return res != 0 ? res : Integer.compare(p1.getAlbumId(), p2.getAlbumId());
    };

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        album = new AlbumImpl(7);
        photo = new Photo[6];
        photo[0] = new Photo(1, 1, "title1", "url1", now.minusDays(7));
        photo[1] = new Photo(1, 2, "title2", "url2", now.minusDays(7));
        photo[2] = new Photo(1, 3, "title3", "url3", now.minusDays(5));
        photo[3] = new Photo(2, 1, "title1", "url1", now.minusDays(7));
        photo[4] = new Photo(2, 4, "title4", "url4", now.minusDays(2));
        photo[5] = new Photo(1, 4, "title4", "url1", now.minusDays(2));

        for (int i = 0; i < photo.length; i++) {
            album.addPhoto(photo[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void addPhoto() {
        assertFalse(album.addPhoto(null));  // null
        assertFalse(album.addPhoto(photo[1]));  // ту же фото

        Photo photo = new Photo(1, 5, "title5", "url5", now.minusDays(3));
        assertTrue(album.addPhoto(photo));
        assertEquals(7, album.size()); // есть место, длинна изменилась на +1

        photo = new Photo(1, 6, "title6", "url6", now.minusDays(3));
        assertFalse(album.addPhoto(photo)); // заполнен альбом
    }

    @org.junit.jupiter.api.Test
    void removePhoto() {
        assertFalse(album.removePhoto(5, 1));

        assertTrue(album.removePhoto(1, 1));
        assertEquals(5, album.size());

        assertNull(album.getPhotoFromAlbum(1, 1));
    }

    @org.junit.jupiter.api.Test
    void updatePhoto() {
        assertTrue(album.updatePhoto(1, 1, "newUrl"));
        assertEquals("newUrl", album.getPhotoFromAlbum(1, 1).getUrl());
    }

    @org.junit.jupiter.api.Test
    void getPhotoFromAlbum() {
        assertEquals(photo[0], album.getPhotoFromAlbum(1, 1));
        assertNull(album.getPhotoFromAlbum(1, 5));
    }

    @org.junit.jupiter.api.Test
    void getAllPhotoFromAlbum() {
        Photo[] actual = album.getAllPhotoFromAlbum(2); // {photo[3], photo[4]} // {photo[4], photo[3]} ?
        Photo[] expected = {photo[3], photo[4]};
        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getPhotoBetweenDate() {
        LocalDate ld = LocalDate.now();
        Photo[] actual = album.getPhotoBetweenDate(ld.minusDays(5), ld.minusDays(2));
        Photo[] expected = {photo[2], photo[5], photo[4]};
        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(6, album.size());
    }
}