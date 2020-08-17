SELECT
  *
FROM
  NOTE_USER_DATA
WHERE
  SEQ_LOGIN_ID = /* seqLoginId */1
  /*%if title != null */
  AND TITLE LIKE /* @infix(title) */'test'
  /*%end*/
ORDER BY
  UPDATE_DATE DESC;
