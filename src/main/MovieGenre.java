package main;

/**
 * A movie genre
 */
public enum MovieGenre {
    ACTION {
        @Override
        public String toString() {
            return "Action";
        }
    },
    ADVENTURE {
        @Override
        public String toString() {
            return "Adventure";
        }
    },
    COMEDY {
        @Override
        public String toString() {
            return "Comedy";
        }
    },
    CRIMEANDMYSTERY {
        @Override
        public String toString() {
            return "Crime and Mystery";
        }
    },
    FANTASY {
        @Override
        public String toString() {
            return "Fantasy";
        }
    },
    HISTORICAL {
        @Override
        public String toString() {
            return "Historical";
        }
    },
    HORROR {
        @Override
        public String toString() {
            return "Horror";
        }
    },
    ROMANCE {
        @Override
        public String toString() {
            return "Romance";
        }
    },
    SATIRE {
        @Override
        public String toString() {
            return "Sattire";
        }
    },
    SCIENCEFICTION {
        @Override
        public String toString() {
            return "Science Fiction";
        }
    },
    SPECULATIVE {
        @Override
        public String toString() {
            return "Speculative";
        }
    },
    THRILLER {
        @Override
        public String toString() {
            return "Thriller";
        }
    },
    WESTERN {
        @Override
        public String toString() {
            return "Western";
        }
    },
    OTHER {
        @Override
        public String toString() {
            return "Other";
        }
    }


}
