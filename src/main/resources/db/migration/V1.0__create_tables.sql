----  Table [dbo].[authors]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[authors](
	[author_id] [bigint] IDENTITY(1,1) NOT NULL,
	[first_name] [varchar](255) NOT NULL,
	[last_name] [varchar](255) NOT NULL,
	[pen_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[author_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[book_authors]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book_authors](
	[book_id] [bigint] NOT NULL,
	[author_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
----  Table [dbo].[book_genres]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book_genres](
	[book_id] [bigint] NOT NULL,
	[genre_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
----  Table [dbo].[books]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[books](
	[book_id] [bigint] IDENTITY(1,1) NOT NULL,
	[isbn] [varchar](255) NOT NULL,
	[price] [bigint] NOT NULL,
	[title] [varchar](255) NOT NULL,
	[year_published] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[book_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[cart_items]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_items](
	[cart_user_id] [bigint] NOT NULL,
	[book_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
----  Table [dbo].[carts]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carts](
	[cart_user_id] [bigint] NOT NULL,
	[date_created] [datetime2](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[cart_user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[genres]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[genres](
	[genre_id] [bigint] IDENTITY(1,1) NOT NULL,
	[genre_title] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[genre_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[order_items]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_items](
	[order_id] [bigint] NOT NULL,
	[book_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
----  Table [dbo].[orders]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [bigint] IDENTITY(1,1) NOT NULL,
	[date_checked_out] [datetime2](6) NULL,
	[has_paid] [bit] NOT NULL,
	[order_reference] [varchar](255) NOT NULL,
	[payment_method] [varchar](255) NULL,
	[total] [bigint] NOT NULL,
	[user_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[payments]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payments](
	[payment_id] [bigint] IDENTITY(1,1) NOT NULL,
	[date_of_payment] [datetime2](6) NULL,
	[order_reference] [varchar](255) NOT NULL,
	[payment_method] [varchar](255) NULL,
	[payment_reference] [varchar](255) NOT NULL,
	[payment_status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[users]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [bigint] IDENTITY(1,1) NOT NULL,
	[date_created] [datetime2](6) NULL,
	[email] [varchar](255) NOT NULL,
	[first_name] [varchar](255) NOT NULL,
	[is_user_enabled] [bit] NULL,
	[last_name] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[role] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
----  Table [dbo].[verification_codes]    ----
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[verification_codes](
	[user_id] [bigint] NOT NULL,
	[expires_at] [datetime2](6) NULL,
	[verification_code] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[book_authors]  WITH CHECK ADD  CONSTRAINT [FKbhqtkv2cndf10uhtknaqbyo0a] FOREIGN KEY([book_id])
REFERENCES [dbo].[books] ([book_id])
GO
ALTER TABLE [dbo].[book_authors] CHECK CONSTRAINT [FKbhqtkv2cndf10uhtknaqbyo0a]
GO
ALTER TABLE [dbo].[book_authors]  WITH CHECK ADD  CONSTRAINT [FKo86065vktj3hy1m7syr9cn7va] FOREIGN KEY([author_id])
REFERENCES [dbo].[authors] ([author_id])
GO
ALTER TABLE [dbo].[book_authors] CHECK CONSTRAINT [FKo86065vktj3hy1m7syr9cn7va]
GO
ALTER TABLE [dbo].[book_genres]  WITH CHECK ADD  CONSTRAINT [FK6ce5ct13whb85xqu83jmceboj] FOREIGN KEY([genre_id])
REFERENCES [dbo].[genres] ([genre_id])
GO
ALTER TABLE [dbo].[book_genres] CHECK CONSTRAINT [FK6ce5ct13whb85xqu83jmceboj]
GO
ALTER TABLE [dbo].[book_genres]  WITH CHECK ADD  CONSTRAINT [FKtqnlma9c5byf3gfqsuu0ebrl5] FOREIGN KEY([book_id])
REFERENCES [dbo].[books] ([book_id])
GO
ALTER TABLE [dbo].[book_genres] CHECK CONSTRAINT [FKtqnlma9c5byf3gfqsuu0ebrl5]
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD  CONSTRAINT [FKhiu1jw80o45wfiw5tgok1xpkl] FOREIGN KEY([book_id])
REFERENCES [dbo].[books] ([book_id])
GO
ALTER TABLE [dbo].[cart_items] CHECK CONSTRAINT [FKhiu1jw80o45wfiw5tgok1xpkl]
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD  CONSTRAINT [FKl6q3soafh5skj3x3hk3qb7o2u] FOREIGN KEY([cart_user_id])
REFERENCES [dbo].[carts] ([cart_user_id])
GO
ALTER TABLE [dbo].[cart_items] CHECK CONSTRAINT [FKl6q3soafh5skj3x3hk3qb7o2u]
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD  CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_items] CHECK CONSTRAINT [FKbioxgbv59vetrxe0ejfubep1w]
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD  CONSTRAINT [FKi4ptndslo2pyfp9r1x0eulh9g] FOREIGN KEY([book_id])
REFERENCES [dbo].[books] ([book_id])
GO
ALTER TABLE [dbo].[order_items] CHECK CONSTRAINT [FKi4ptndslo2pyfp9r1x0eulh9g]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK32ql8ubntj5uh44ph9659tiih] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK32ql8ubntj5uh44ph9659tiih]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD CHECK  (([payment_method]='USSD' OR [payment_method]='BANK_TRANSFER' OR [payment_method]='WEB'))
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD CHECK  (([payment_method]='USSD' OR [payment_method]='BANK_TRANSFER' OR [payment_method]='WEB'))
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD CHECK  (([payment_status]='SUCCESSFUL' OR [payment_status]='FAILED' OR [payment_status]='PENDING'))
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD CHECK  (([role]='ROLE_ADMIN' OR [role]='ROLE_USER'))
GO
