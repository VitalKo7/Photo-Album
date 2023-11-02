package album.dao;

import album.model.Photo;

import java.time.LocalDate;

public class AlbumImpl implements Album{
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        this.photos = new Photo[capacity];
    }

    // TODO: прописать методы
    @Override
    public boolean addPhoto(Photo photo) {
        // ! добавлять в конец
        // 2* при добавлении поддерживать массив в сортированном виде
        return false;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        // 2 способа: последний на место удаленного или
        // 2* при добавлении поддерживать массив в сортированном виде
        return false;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        return false;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        return new Photo[0];
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        // как из LocalDate получить LocalDateTime
        // если массив сортирован - как пользоваться бинарным поиском?
        return new Photo[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
