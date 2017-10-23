package io.skygear.skygear;

import org.json.JSONObject;

/**
 * The Error for the response of Skygear Request.
 */
public class Error extends Exception {
    private final int codeValue;
    private final String name;
    private final String detailMessage;
    private final JSONObject info;

    /**
     * Instantiates a new Error.
     * <p>
     *     This constructor will creates an Error with
     *     error code {@link Code#UNEXPECTED_ERROR}
     * </p>
     *
     * @param detailMessage the detail message
     */
    public Error(String detailMessage) {
        this(Code.UNEXPECTED_ERROR.getValue(), null, detailMessage, null, null);
    }

    /**
     * Instantiates a new Error.
     * <p>
     *     This constructor will creates an Error with
     *     error code {@link Code#UNEXPECTED_ERROR}
     * </p>
     *
     * @param detailMessage the detail message
     * @param throwable error exception
     */
    public Error(String detailMessage, Throwable throwable) {
        this(Code.UNEXPECTED_ERROR.value, null, detailMessage, null, throwable);
    }

    /**
     * Instantiates a new Error.
     * <p>
     *     This constructor will creates an Error and try to
     *     parse the error code value.
     * </p>
     *
     * @param codeValue     the code value
     * @param detailMessage the detail message
     */
    public Error(int codeValue, String detailMessage) {
        this(codeValue, null, detailMessage, null, null);
    }

    /**
     * Instantiates a new Error.
     *
     * @param codeValue     the code
     * @param name          the name of the error
     * @param detailMessage the detail message
     * @param info          the info from error message
     */
    public Error(int codeValue, String name, String detailMessage, JSONObject info) {
        this(codeValue, name, detailMessage, info, null);
    }

    /**
     * Instantiates a new Error.
     *
     * @param codeValue     the code
     * @param name          the name of the error
     * @param detailMessage the detail message
     * @param info          the info from error message
     * @param throwable     error exception
     */
    public Error(int codeValue, String name, String detailMessage, JSONObject info, Throwable throwable) {
        super(Code.fromValue(codeValue).toString(), throwable);

        this.codeValue = codeValue;
        this.name = name;
        this.detailMessage = detailMessage;
        this.info = info;
    }

    /**
     * Gets error code.
     *
     * @return the code
     */
    public Code getCode() {
        return Code.fromValue(this.codeValue);
    }

    /**
     * The Error Codes.
     */
    public static enum Code {
        /**
         * Not authenticated error.
         */
        NOT_AUTHENTICATED(101),
        /**
         * Permission denied error.
         */
        PERMISSION_DENIED(102),
        /**
         * Access key not accepted error.
         */
        ACCESS_KEY_NOT_ACCEPTED(103),
        /**
         * Access token not accepted error.
         */
        ACCESS_TOKEN_NOT_ACCEPTED(104),
        /**
         * Invalid credentials error.
         */
        INVALID_CREDENTIALS(105),
        /**
         * Invalid signature error.
         */
        INVALID_SIGNATURE(106),
        /**
         * Bad request error.
         */
        BAD_REQUEST(107),
        /**
         * Invalid argument error.
         */
        INVALID_ARGUMENT(108),
        /**
         * Duplicated error.
         */
        DUPLICATED(109),
        /**
         * Resource not found error.
         */
        RESOURCE_NOT_FOUND(110),
        /**
         * Not supported error.
         */
        NOT_SUPPORTED(111),
        /**
         * Not implemented error.
         */
        NOT_IMPLEMENTED(112),
        /**
         * Constraint violated error.
         */
        CONSTRAINT_VIOLATED(113),
        /**
         * Incompatible schema error.
         */
        INCOMPATIBLE_SCHEMA(114),
        /**
         * Atomic operation failure error.
         */
        ATOMIC_OPERATION_FAILURE(115),
        /**
         * Partial operation failure error.
         */
        PARTIAL_OPERATION_FAILURE(116),
        /**
         * Undefined operation error.
         */
        UNDEFINED_OPERATION(117),
        /**
         * Plugin unavailable error.
         */
        PLUGIN_UNAVAILABLE(118),
        /**
         * Plugin timeout error.
         */
        PLUGIN_TIMEOUT(119),
        /**
         * Record query invalid error.
         */
        RECORD_QUERY_INVALID(120),
        /**
         * Plugin initializing error.
         */
        PLUGIN_INITIALIZING(121),

        /**
         * Unexpected error.
         */
        UNEXPECTED_ERROR(10000);

        private final int value;

        private Code(int value) {
            this.value = value;
        }

        /**
         * Gets value of the error code.
         *
         * @return the value
         */
        public int getValue() {
            return this.value;
        }

        /**
         * Creates error code from value.
         *
         * @param value the value
         * @return the code
         */
        public static Code fromValue(int value) {
            Code[] codes = Code.values();
            for (Code eachCode : codes) {
                if (eachCode.getValue() == value) {
                    return eachCode;
                }
            }

            return Code.UNEXPECTED_ERROR;
        }

        @Override
        public String toString() {
            switch (this) {
                case NOT_AUTHENTICATED:
                    return "You have to be authenticated to perform this operation.";
                case PERMISSION_DENIED:
                case ACCESS_KEY_NOT_ACCEPTED:
                case ACCESS_TOKEN_NOT_ACCEPTED:
                    return "You are not allowed to perform this operation.";
                case INVALID_CREDENTIALS:
                    return "You are not allowed to log in because the credentials you provided are not valid.";
                case INVALID_SIGNATURE:
                case BAD_REQUEST:
                    return "The server is unable to process the request.";
                case INVALID_ARGUMENT:
                    return "The server is unable to process the data.";
                case DUPLICATED:
                    return "This request contains duplicate of an existing resource on the server.";
                case RESOURCE_NOT_FOUND:
                    return "The requested resource is not found.";
                case NOT_SUPPORTED:
                    return "This operation is not supported.";
                case NOT_IMPLEMENTED:
                    return "This operation is not implemented.";
                case CONSTRAINT_VIOLATED:
                case INCOMPATIBLE_SCHEMA:
                case ATOMIC_OPERATION_FAILURE:
                case PARTIAL_OPERATION_FAILURE:
                    return "A problem occurred while processing this request.";
                case UNDEFINED_OPERATION:
                    return "The requested operation is not available.";
                case PLUGIN_INITIALIZING:
                case PLUGIN_UNAVAILABLE:
                    return "The server is not ready yet.";
                case PLUGIN_TIMEOUT:
                    return "The server took too long to process.";
                case RECORD_QUERY_INVALID:
                    return "A problem occurred while processing this request.";
                case UNEXPECTED_ERROR:
                    return "An unexpected error has occurred.";
            }
            return super.toString();
        }
    }

    public String getName () {
        return this.name;
    }
    public int getCodeValue() {
        return this.codeValue;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public JSONObject getInfo() {
        return this.info;
    }
}
