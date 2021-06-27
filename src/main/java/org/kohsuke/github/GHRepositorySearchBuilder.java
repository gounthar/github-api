package org.kohsuke.github;

/**
 * Search repositories.
 *
 * @author Kohsuke Kawaguchi
 * @see GitHub#searchRepositories() GitHub#searchRepositories()
 */
public class GHRepositorySearchBuilder extends GHSearchBuilder<GHRepository> {
    GHRepositorySearchBuilder(GitHub root) {
        super(root, RepositorySearchResult.class);
    }

    /**
     * Search terms.
     */
    public GHRepositorySearchBuilder q(String term) {
        super.q(term);
        return this;
    }

    /**
     * In gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder in(String v) {
        return q("in:" + v);
    }

    /**
     * Size gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder size(String v) {
        return q("size:" + v);
    }

    /**
     * Forks gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder forks(String v) {
        return q("forks:" + v);
    }

    /**
     * Searching in forks
     *
     * By default, forks are not shown in search results. Forks are only indexed for code search when they have more
     * stars than the parent repository. You will not be able to search the code in a fork that has less stars than its
     * parent. To show forks with more stars than the parent repository in code search results, add
     * Fork.ALL_INCLUDING_FORKS or Fork.FORKS_ONLY.
     *
     * @param fork
     *            search mode for forks
     *
     * @return the gh repository search builder
     *
     * @see <a href=
     *      "https://docs.github.com/en/github/searching-for-information-on-github/searching-on-github/searching-in-forks">Searching
     *      in forks</a>
     *
     */
    public GHRepositorySearchBuilder fork(Fork fork) {
        if (fork == Fork.DEFAULT) {
            this.terms.removeIf(term -> term.contains("fork:"));
            return this;
        }

        return q("fork:" + fork);
    }

    /**
     * Search by repository visibility
     *
     * @param visibility
     *            repository visibility
     *
     * @return the gh repository search builder
     *
     * @see <a href=
     *      "https://docs.github.com/en/github/searching-for-information-on-github/searching-on-github/searching-for-repositories#search-by-repository-visibility">Search
     *      by repository visibility</a>
     *
     */
    public GHRepositorySearchBuilder visibility(GHRepository.Visibility visibility) {
        if (visibility == GHRepository.Visibility.UNKNOWN) {
            return this;
        }

        return q("is:" + visibility);
    }

    /**
     * Created gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder created(String v) {
        return q("created:" + v);
    }

    /**
     * Pushed gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder pushed(String v) {
        return q("pushed:" + v);
    }

    /**
     * User gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder user(String v) {
        return q("user:" + v);
    }

    /**
     * Repo gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder repo(String v) {
        return q("repo:" + v);
    }

    /**
     * Language gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder language(String v) {
        return q("language:" + v);
    }

    /**
     * Stars gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder stars(String v) {
        return q("stars:" + v);
    }

    /**
     * Topic gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder topic(String v) {
        return q("topic:" + v);
    }

    /**
     * Order gh repository search builder.
     *
     * @param v
     *            the v
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder order(GHDirection v) {
        req.with("order", v);
        return this;
    }

    /**
     * Sort gh repository search builder.
     *
     * @param sort
     *            the sort
     * @return the gh repository search builder
     */
    public GHRepositorySearchBuilder sort(Sort sort) {
        req.with("sort", sort);
        return this;
    }

    /**
     * The enum Sort.
     */
    public enum Sort {
        STARS, FORKS, UPDATED
    }

    /**
     * The enum Fork.
     *
     * By default, forks are not shown in search results. Forks are only indexed for code search when they have more
     * stars than the parent repository. You will not be able to search the code in a fork that has less stars than its
     * parent. To show forks with more stars than the parent repository in code search results, add
     * Fork.ALL_INCLUDING_FORKS or Fork.FORKS_ONLY.
     */
    public enum Fork {
        ALL_INCLUDING_FORKS("true"), FORKS_ONLY("only"), DEFAULT("ignore");

        private String filterMode;
        Fork(String mode) {
            this.filterMode = mode;
        }

        public String toString() {
            return filterMode;
        }
    }

    private static class RepositorySearchResult extends SearchResult<GHRepository> {
        private GHRepository[] items;

        @Override
        GHRepository[] getItems(GitHub root) {
            for (GHRepository item : items)
                item.wrap(root);
            return items;
        }
    }

    @Override
    protected String getApiUrl() {
        return "/search/repositories";
    }
}
