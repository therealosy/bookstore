USE [bookstore]
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (1, N'George', N'Martin', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (2, N'Suzanne', N'Collins', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (3, N'Chinua', N'Achiebe', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (4, N'James', N'Patterson', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (5, N'Chimamanda', N'Adichie', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (6, N'John', N'Tolkien', NULL)
GO
INSERT [dbo].[authors] ([author_id], [first_name], [last_name], [middle_name]) VALUES (7, N'Andy', N'Weir', NULL)
GO
INSERT [dbo].[books] ([book_id], [isbn], [price], [title], [year_published]) VALUES (1, N'978-3-16-148410-0', 100000, N'The Martian', 2011)
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
INSERT [dbo].[book_authors] ([author_id], [book_id]) VALUES (7, 1)
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
INSERT [dbo].[users] ([user_id], [date_created], [email], [first_name], [is_user_enabled], [last_name], [password], [role]) VALUES (1, NULL, N'admin@bookstore.com', N'Bookstore', 1, N'Admin', N'$2a$10$1z6iqQqEmx.w2qMmaVn/auRU7w4j83u1wlNqVJpOGMqrhRV6uno4e', N'ROLE_ADMIN')
GO
