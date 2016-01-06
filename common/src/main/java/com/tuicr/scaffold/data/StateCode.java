package com.tuicr.scaffold.data;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.authcenter
 * @date 15/12/16
 */
public interface StateCode {


    int SUCCESS = 1;
    int FAILURE = -1;


    int ERROR = 90000;

    /**
     * 数据库通用异常码
     */
    int ERROR_DB = ERROR + 1000;

    /**
     * 唯一性
     */
    int ERRORDB_UNIQUE = ERROR_DB + 1;

    /**
     * 用户错误
     */
    int ERROR_ACCOUNT = ERROR - 10000;


    /**
     * 账户锁定
     */
    int ERROR_ACCOUNT_LOCK = ERROR_ACCOUNT + 1;


    /**
     * 账户过期
     */
    int ERROR_ACCOUNT_EXPIRE = ERROR_ACCOUNT_LOCK + 1;


    /**
     * 证书过期
     * credentials NonExpired
     */
    int ERROR_ACCOUNT_CREDENTIALS_EXPIRE = ERROR_ACCOUNT_EXPIRE + 1;

    /**
     * 用户未找到
     */
    int ERROR_ACCOUNT_NOTFOUND = ERROR_ACCOUNT_CREDENTIALS_EXPIRE + 1;


    /**
     * 没有platform
     */
    int ERROR_ACCOUNT_NOTPLATFORM = ERROR_ACCOUNT_CREDENTIALS_EXPIRE + 1;

    /**
     * 密码错误
     */
    int ERROR_ACCOUNT_PWD_INVALID = ERROR_ACCOUNT_NOTPLATFORM + 1;


    enum Error {

        //账户相关异常
        ERROR_ACCOUNT(StateCode.ERROR_ACCOUNT, ""),
        ERROR_ACCOUNT_LOCK(StateCode.ERROR_ACCOUNT_LOCK, "account.lock"),
        ERROR_ACCOUNT_EXPIRE(StateCode.ERROR_ACCOUNT_EXPIRE, "account.expire"),
        ERROR_ACCOUNT_CREDENTIALS_EXPIRE(StateCode.ERROR_ACCOUNT_CREDENTIALS_EXPIRE, "account.credentials.expire"),
        ERROR_ACCOUNT_NOTFOUND(StateCode.ERROR_ACCOUNT_NOTFOUND, "account.notfound"),
        ERROR_ACCOUNT_NOTPLATFORM(StateCode.ERROR_ACCOUNT_NOTPLATFORM, "account.login.notplatform"),
        ERROR_ACCOUNT_PWD_INVALID(StateCode.ERROR_ACCOUNT_PWD_INVALID,"common.error.pwdinvalid"),
        //数据库相关异常
        ERROR_DB(StateCode.ERROR_DB, ""),
        ERRORDB_UNIQUE(StateCode.ERRORDB_UNIQUE, "common.error.unique");

        long errorCode = FAILURE;
        String errorMsg = "";

        Error(long errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }

        public long getErrorCode() {
            return errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }


}
