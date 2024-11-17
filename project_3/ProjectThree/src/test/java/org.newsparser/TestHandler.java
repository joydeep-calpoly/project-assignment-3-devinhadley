package org.newsparser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TestHandler extends Handler {

        /*
        A handler used for assertions on log output.
        Assign to logger in setup of any test suite that requires assertions on log output.
         */

        private final List<String> messages = new java.util.ArrayList<>();

        @Override
        public void publish(LogRecord record) {
            if (isLoggable(record)) {
                messages.add(record.getMessage());
            }
        }
        @Override
        public void flush() {
            messages.clear();
        }

        @Override
        public void close() throws SecurityException {}

        public List<String> getMessages() {
            return new ArrayList<>(messages);
        }
}
