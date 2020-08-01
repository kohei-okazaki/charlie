-- 定時情報マスタ
CREATE TABLE IF NOT EXISTS REGULAR_WORK_MT (
  -- 定時情報マスタID
  SEQ_REGULAR_WORK_MT_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '定時情報マスタID',
  -- 始業時間(時)
  BEGIN_HOUR INT NOT NULL COMMENT '始業時間(時)',
  -- 始業時間(分)
  BEGIN_MINUTE INT NOT NULL COMMENT '始業時間(分)',
  -- 終業時間(時)
  END_HOUR INT NOT NULL COMMENT '終業時間(時)',
  -- 終業時間(分)
  END_MINUTE INT NOT NULL COMMENT '終業時間(分)',
  -- バージョン情報
  VERSION INT NOT NULL COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE TIMESTAMP NOT NULL COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE TIMESTAMP NOT NULL COMMENT '更新日時'
);