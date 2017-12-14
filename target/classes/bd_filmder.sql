drop database bd_filmder;

create database bd_filmder;

use bd_filmder;

create table tb_peliculas(
	id_p			int auto_increment primary key,
    titulo			varchar(255),
    sinopsis		varchar(500),
    genero_p		varchar(255),
    censura			int, -- 0 apta para todos, 1 mayores de 14
    fecha_estreno	date,
    duracion		int, -- minutos
    actores			varchar(255),
    director		varchar(255),
    productora		varchar(255),
    url_portada		varchar(255),
    url_trailer		varchar(255)
);

create table tb_usuarios(
	id_u				int auto_increment primary key,
    nombre				varchar(50),
    apellido			varchar(50),
    correo				varchar(100),
    contrasena			varchar(255),
    nacimiento			date,
    genero_u			int, -- 0 hombre, 1 mujer
    tipo				int -- 0 admin, 1 comun
);

create table tb_matchs(
	id_pelicula		int,
    id_usuario		int,
    matched			int,-- 0 false | 1 true
    foreign key(id_pelicula) references tb_peliculas(id_p),
    foreign key(id_usuario) references tb_usuarios(id_u),
    primary key(id_pelicula,id_usuario) 
);

insert into tb_peliculas values(null,
								'Linterna Verde', 
								'En un universo tan extenso como misterioso una pequena pero poderosa fuerza ha existido durante siglos. Protectores de la paz y la justicia, reciben el nombre de Linterna Verde. Una hermandad de guerreros que han jurado mantener el orden intergalactico, donde cada Linterna Verde lleva un anillo que le concede sus superpoderes, alimentado por el poder verde de la voluntad designando a cada Linterna Verde a cada uno de los 3600 sectores del universo.', 
								'Acción, Ficción',
								0, 
								'2017-10-22', 
								125, 
								'Italo Contreras, Luis Acosta',
								'Alexandre Castillo', 
								'Marvel', 
								'https://res.cloudinary.com/alexandrecg/image/upload/v1508608370/linterna_verde.jpg',
								'https://www.youtube.com/watch?v=_T7_JCD0dzU');
insert into tb_peliculas values(null, 
								'Thor: Ragnarok', 
								'Es una película de superhéroes estadounidense basada en el personaje de Marvel Comics Thor, producida por Marvel Studios y distribuida por Walt Disney Studios Motion Pictures. ', 
								'Ficción', 
								0, 
								'2017-11-05', 
								202, 
								'Chris Hemsworth, Tom Hiddleston, Mark Ruffalo',
								'Taika Waititi', 
								'DC comics', 
								'https://www.bleedingcool.com/wp-content/uploads/2017/08/DH_uCKMV0AI_dbF-1.jpg', 
								'https://www.youtube.com/watch?v=-gpggzJ9PL4');
insert into tb_peliculas values(null,
								'It', 
								'Son los años 80 en el pequeño pueblo de Derry, en el estado de Maine. En él vive una panda de siete niños conocidos como "El club de los perdedores", que debe enfrentarse a sus problemas cotidianos con los matones de la escuela. Pero su vida da un giro inesperado cuando, durante el verano, una gran amenaza se cierne sobre ellos después de que una oleada de extrañas muertes provoquen el pánico y el terror entre los habitantes del lugar. ', 
								'Terror',
								1, 
								'2017-10-22', 
								125, 
								'Bill Skarsgard, Finn Wolfhard, Jaeden Lieberher, Sophia Lillis',
								'Andrés Muschietti', 
								'New Line Cinema', 
								'http://es.web.img3.acsta.net/c_215_290/pictures/17/04/07/12/58/197841.jpg',
								'https://www.youtube.com/watch?v=b34hTfJMOiQ');
insert into tb_peliculas values(null, 
								'Avengers: Infinity War', 
								'Mientras que Los Vengadores y sus aliados continúan protegiendo al mundo de largas tiranías, un nuevo peligro emerge de las sombras cósmicas, un infame villano intergaláctico llamado Thanos. Su meta es coleccionar todas las gemas del infinito, artefactos de un inimaginable poder, para usarlos en beneficio propio y alterar el universo entero.',
								'Accion, Ficcion', 
								0, 
								'2010-10-10', 
								202, 
								'Robert Downey Jr.,Josh Brolin,Chris Evans',
								'Kevin Feige', 
								'Marvel', 
								'https://orig00.deviantart.net/1c15/f/2017/177/b/7/avengers__infinity_war_poster__3_by_bakikayaa-dbe59vp.jpg',
								'https://www.youtube.com/watch?v=ESE6u5aE740');
insert into tb_peliculas values(null,
								'Jigsaw: el juego continúa', 
								'En un universo tan extenso como misterioso una pequena pero poderosa fuerza ha existido durante siglos. Protectores de la paz y la justicia, reciben el nombre de Linterna Verde. Una hermandad de guerreros que han jurado mantener el orden intergalactico, donde cada Linterna Verde lleva un anillo que le concede sus superpoderes, alimentado por el poder verde de la voluntad designando a cada Linterna Verde a cada uno de los 3600 sectores del universo.', 
								'Suspenso, Terror',
								1, 
								'2017-09-24', 
								125, 
								'Tobin Bell',
								'Spierig brothers', 
								'Warner Bros', 
								'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr93RElgVFK7o3-Hj_UjT6yELx40D-XV_57zzgp0QMJmQKksJ2',
								'https://www.youtube.com/watch?v=MvcVw_i4EBU');
insert into tb_peliculas values(null, 
								'Pantera Negra', 
								'Accion, Ficcion', 
								'T’Challa, de Marvel Studios, sigue a T’Challa, quien después de la muerte de su padre, el rey de Wakanda, regresa a su solitaria nación africana de tecnología avanzada para tomar su lugar como legítimo rey. Pero cuando un antiguo y poderoso enemigo reaparece, la fortaleza de T’Challa como rey y superhéroe es puesta a prueba cuando se ve involucrado en un gran conflicto que pone en riesgo el destino de Wakanda y del mundo entero. Ante la traición y el peligro, el joven rey debe unirse a sus aliados y liberar todo el poder de Black Panther para derrotar a sus enemigos y garantizar la seguridad de su pueblo y su estilo de vida.', 
								0, 
								'2018-03-15', 
								202, 
								'Chadwick Boseman, Michael B. Jordan, Lupita Nyong ´o´',
								'Kevin Feige', 
								'Marvel', 
								'http://img.aullidos.com/imagenes/caratulas/black-panther-2.jpg', 
								'https://www.youtube.com/watch?v=R7jYehlp_dw');
--
insert into tb_peliculas values(null,
								'Maze Runner: The Death Cure', 
								'En este final épico de la saga Maze Runner, Thomas lidera su grupo de Gladers escapados de la última y difícil misión. Para salvar a sus amigos, deben entrar en la legendaria Last City, un laberinto controlado por WCKD que puede llegar a ser el laberinto más mortífero de todos. Cualquier persona que lo consiga pasar con vida obtendrá respuestas a las preguntas que los Gladers se han estado haciendo desde que llegaron por primera vez al laberinto.',
								'Terror',
								0, 
								'2018-01-06', 
								130, 
								'Bill Skarsgard, Finn Wolfhard, Jaeden Lieberher, Sophia Lillis',
								'Wes Ball', 
								'20th Century Fox', 
								'https://images-na.ssl-images-amazon.com/images/M/MV5BMTY1YjU2MTctMDgwYS00ZTA4LTk4YjctYTE4MzZkOTQ1ODdmXkEyXkFqcGdeQXVyNzA0OTkwNDk@._V1_UX182_CR0,0,182,268_AL_.jpg',
								'https://www.youtube.com/watch?v=j61Uz3iDZ0o');
insert into tb_peliculas values(null, 
								'Avengers: Infinity War', 
								'Mientras que Los Vengadores y sus aliados continúan protegiendo al mundo de largas tiranías, un nuevo peligro emerge de las sombras cósmicas, un infame villano intergaláctico llamado Thanos. Su meta es coleccionar todas las gemas del infinito, artefactos de un inimaginable poder, para usarlos en beneficio propio y alterar el universo entero.',
								'Accion, Ficcion', 
								0, 
								'2010-10-10', 
								202, 
								'Robert Downey Jr.,Josh Brolin,Chris Evans',
								'Kevin Feige', 
								'Marvel', 
								'https://orig00.deviantart.net/1c15/f/2017/177/b/7/avengers__infinity_war_poster__3_by_bakikayaa-dbe59vp.jpg',
								'https://www.youtube.com/watch?v=ESE6u5aE740');
insert into tb_peliculas values(null,
								'Jigsaw: el juego continúa', 
								'En un universo tan extenso como misterioso una pequena pero poderosa fuerza ha existido durante siglos. Protectores de la paz y la justicia, reciben el nombre de Linterna Verde. Una hermandad de guerreros que han jurado mantener el orden intergalactico, donde cada Linterna Verde lleva un anillo que le concede sus superpoderes, alimentado por el poder verde de la voluntad designando a cada Linterna Verde a cada uno de los 3600 sectores del universo.', 
								'Suspenso, Terror',
								1, 
								'2017-09-24', 
								125, 
								'Tobin Bell',
								'Spierig brothers', 
								'Marvel', 
								'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr93RElgVFK7o3-Hj_UjT6yELx40D-XV_57zzgp0QMJmQKksJ2',
								'https://www.youtube.com/watch?v=MvcVw_i4EBU');
insert into tb_peliculas values(null, 
								'Pantera Negra', 
								'Accion, Ficcion', 
								'T’Challa, de Marvel Studios, sigue a T’Challa, quien después de la muerte de su padre, el rey de Wakanda, regresa a su solitaria nación africana de tecnología avanzada para tomar su lugar como legítimo rey. Pero cuando un antiguo y poderoso enemigo reaparece, la fortaleza de T’Challa como rey y superhéroe es puesta a prueba cuando se ve involucrado en un gran conflicto que pone en riesgo el destino de Wakanda y del mundo entero. Ante la traición y el peligro, el joven rey debe unirse a sus aliados y liberar todo el poder de Black Panther para derrotar a sus enemigos y garantizar la seguridad de su pueblo y su estilo de vida.', 
								0, 
								'2018-03-15', 
								202, 
								'Chadwick Boseman, Michael B. Jordan, Lupita Nyong ´o´',
								'Kevin Feige', 
								'Marvel', 
								'http://img.aullidos.com/imagenes/caratulas/black-panther-2.jpg', 
								'https://www.youtube.com/watch?v=R7jYehlp_dw');								

-- LAS PELICULAS QUE AUN NO HA DECIDIDO, SI SI O NO EL USUARIO = ?
select p.* from tb_peliculas p where id_p not in (select id_pelicula from tb_matchs where id_usuario = 1);