-- 勤怠ユーザ履歴マスタ
CREATE TABLE IF NOT EXISTS WORK_USER_HIST_MT (
  -- 勤怠ユーザ履歴マスタID
  SEQ_WORK_USER_HIST_MT_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '勤怠ユーザ履歴マスタID',
  -- 勤怠ユーザ管理マスタID
  SEQ_WORK_USER_MNG_MT_ID INT NOT NULL COMMENT '勤怠ユーザ管理マスタID',
  -- 勤怠ユーザ詳細マスタID
  SEQ_WORK_USER_DETAIL_MT_ID INT NOT NULL COMMENT '勤怠ユーザ詳細マスタID',
  -- ログインID
  SEQ_LOGIN_ID INT NOT NULL COMMENT 'ログインID',
  -- 定時情報マスタID
  SEQ_REGULAR_WORK_MT_ID INT NOT NULL COMMENT '定時情報マスタID',
  -- バージョン情報
  VERSION INT NOT NULL COMMENT 'バージョン情報',
  -- 登録日時
  REG_DATE DATETIME NOT NULL COMMENT '登録日時',
  -- 更新日時
  UPDATE_DATE DATETIME NOT NULL COMMENT '更新日時'
);