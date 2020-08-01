SELECT
  *
FROM
  WORK_USER_MT
WHERE
  SEQ_WORK_USER_MT_ID = (
    SELECT
      MAX(SEQ_WORK_USER_MT_ID)
    FROM
      WORK_USER_MT
    WHERE
      SEQ_LOGIN_ID = /* seqLoginId */2
  );
