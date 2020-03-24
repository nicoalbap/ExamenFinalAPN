use spotify;

Select username from users
where username like'%k%'
and id order by username;

Select title from songs
where plays > (Select avg (plays) from songs);

Select songs.title from songs
inner join albums on songs.album = albums.id
inner join genres on albums.genre = genres.id
where genres.name = 'Pop';

Select * from albums;
 Select * from songs;
 Select * from artists;
 Select * from genres;