-- メールユーザ情報
CREATE TABLE IF NOT EXISTS MAIL_USER_DATA (
  -- メールユーザID
  SEQ_MAIL_USER_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT 'メールユーザID',
  -- ログインID
  SEQ_LOGIN_ID INT NOT NULL COMMENT 'ログインID',
  -- メールアドレス
  MAIL_ADDRESS VARBINARY(1024) NOT NULL COMMENT 'メールアドレス',
  -- バージョン情報
  VERSION INT NOT NULL COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP NOT NULL COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP NOT NULL COMMENT '更新日時'
);