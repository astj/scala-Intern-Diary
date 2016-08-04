CREATE TABLE user (
  `id` BIGINT UNSIGNED NOT NULL,
  `name` VARBINARY(32) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (name),
  KEY (created_at),
  KEY (updated_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE blog (
  `id` BIGINT UNSIGNED NOT NULL,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `name` VARBINARY(32) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (name),
  KEY (user_id),
  KEY (created_at),
  KEY (updated_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE entry (
  `id` BIGINT UNSIGNED NOT NULL,
  `blog_id` BIGINT UNSIGNED NOT NULL,
  `title` VARBINARY(32) NOT NULL,
  `body` MEDIUMBLOB NOT NULL,
  `date` DATETIME NOT NULL, -- 表に表示する公開日
  `created_at` DATETIME NOT NULL, -- 実際に作ったタイミング
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (id),
  KEY (blog_id),
  KEY (date),
  KEY (created_at),
  KEY (updated_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
