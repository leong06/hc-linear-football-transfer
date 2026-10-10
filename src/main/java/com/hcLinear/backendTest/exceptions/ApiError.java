package com.hcLinear.backendTest.exceptions;

import java.util.List;

public record ApiError(String type, String message, List<String> details) {
}
