package ramin.seyghaly.fragmentation.exceptions;

public class FragmentationException extends RuntimeException {
    public static final String THE_VIEW_IS_NOT_SET = "The view is not set";
    public static final String DO_NOT_USE_ON_CREATE_VIEW = "Do not use onCreateView in your custom bottomsheet.";

    public FragmentationException(String message) {
        super(message);
    }

}
