USE [bookstore]
GO
SET IDENTITY_INSERT [dbo].[authors] ON
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (1, N'George', N'Martin', N'George R. R. Martin')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (2, N'Suzanne', N'Collins', N'Suzanne Collins')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (3, N'Chinua', N'Achiebe', N'Chinua Achiebe')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (4, N'James', N'Patterson', N'James Patterson')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (5, N'Chimamanda', N'Adichie', N'Chimamanda Adichie')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (6, N'John', N'Tolkien', N'John Ronald Reuel Tolkien')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (7, N'Andy', N'Weir', N'Andy Weir')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (8, N'Maxine', N'Paetro', N'Maxine Paetro')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (9, N'Stephen', N'King', N'Stephen King')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (10, N'Eric', N'Blair', N'George Orwell')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (11, N'Anne', N'Carson', N'Anne Carson')
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [pen_name]) VALUES (12, N'Madeline', N'Miller', N'Madeline Miller')
GO
SET IDENTITY_INSERT [dbo].[authors] OFF
GO
SET IDENTITY_INSERT [dbo].[books] ON
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (1, N'978-3-16-148410-0', 1000000, N'The Martian', 2011)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (2, N'978-1-4028-9462-6', 1200000, N'The Lord of the Rings', 1954)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (3, N'978-0-306-40615-7', 400000, N'The Hunger Games', 2008)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (4, N'978-0-596-52068-7', 200000, N'Sail', 2008)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (5, N'978-1-59184-461-0', 900000, N'The 8th Confession', 2008)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (6, N'978-0-262-13472-9', 400000, N'Things Fall Apart', 1954)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (7, N'978-0-201-53082-4', 300000, N'There Was a Country', 2011)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (8, N'978-0-399-15396-0', 200000, N'Half of a Yellow Sun', 2006)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (9, N'978-1-56619-909-4', 800000, N'Purple Hibiscus', 2003)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (10, N'978-1-86197-876-9', 700000, N'A Game of Thrones', 1996)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (11, N'978-1-56619-909-4', 200000, N'IT', 1986)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (12, N'978-1-86197-271-5', 1500000, N'The Shining', 1977)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (13, N'978-0-262-13472-9', 1800000, N'Animal Farm', 1945)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (14, N'978-0-201-53082-8', 2000000, N'Nineteen Eighty-Four (1984)', 1949)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (15, N'978-0-395-36341-6', 1200000, N'Plainwater', 1996)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (16, N'978-0-465-02556-2', 1000000, N'Sons of Achilles', 2018)
GO
SET IDENTITY_INSERT [dbo].[books] OFF
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (1, 7)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (2, 6)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (3, 2)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (4, 4)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (5, 4)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (6, 3)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (7, 3)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (8, 5)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (9, 5)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (10, 1)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (5, 8)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (11, 9)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (12, 9)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (13, 10)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (14, 10)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (15, 11)
GO
INSERT [dbo].[book_authors] ([book_id], [author_id]) VALUES (16, 12)
GO
SET IDENTITY_INSERT [dbo].[genres] ON
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (1, N'Fiction')
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (2, N'Thriller')
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (3, N'Mystery')
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (4, N'Poetry')
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (5, N'Horror')
GO
INSERT [dbo].[genres] ([genre_id], [genre_title]) VALUES (6, N'Satire')
GO
SET IDENTITY_INSERT [dbo].[genres] OFF
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (1, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (1, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (2, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (3, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (3, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (4, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (4, 3)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (5, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (5, 3)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (6, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (8, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (9, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (10, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (11, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (11, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (11, 5)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (12, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (12, 2)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (12, 5)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (13, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (13, 6)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (14, 1)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (14, 6)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (15, 4)
GO
INSERT [dbo].[book_genres] ([book_id], [genre_id]) VALUES (16, 4)
GO
SET IDENTITY_INSERT [dbo].[users] ON
GO
INSERT [dbo].[users] ([user_id], [date_created], [email], [first_name], [is_user_enabled], [last_name], [password], [role]) VALUES (1, CAST(N'2023-12-20T19:28:39.5360000' AS DateTime2), N'admin@bookstore.com', N'Bookstore', 1, N'Admin', N'$2a$10$1z6iqQqEmx.w2qMmaVn/auRU7w4j83u1wlNqVJpOGMqrhRV6uno4e', N'ROLE_ADMIN')
GO
SET IDENTITY_INSERT [dbo].[users] OFF
GO