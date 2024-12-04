package org.kohsuke.github;

import org.kohsuke.github.GHRepository.Visibility;

import java.io.IOException;
import java.net.URL;

abstract class GHRepositoryBuilder<S> extends AbstractBuilder<GHRepository, S> {

    private boolean defaultBranchOnly = false;

    protected GHRepositoryBuilder(Class<S> intermediateReturnType, GitHub root, GHRepository baseInstance) {
        super(GHRepository.class, intermediateReturnType, root, baseInstance);
    }

    public S allowSquashMerge(boolean enabled) throws IOException {
        return with("allow_squash_merge", enabled);
    }

    public S allowMergeCommit(boolean enabled) throws IOException {
        return with("allow_merge_commit", enabled);
    }

    public S allowRebaseMerge(boolean enabled) throws IOException {
        return with("allow_rebase_merge", enabled);
    }

    public S allowForking(boolean enabled) throws IOException {
        return with("allow_forking", enabled);
    }

    public S deleteBranchOnMerge(boolean enabled) throws IOException {
        return with("delete_branch_on_merge", enabled);
    }

    public S defaultBranch(String branch) throws IOException {
        return with("default_branch", branch);
    }

    public S description(String description) throws IOException {
        return with("description", description);
    }

    public S homepage(URL homepage) throws IOException {
        return homepage(homepage.toExternalForm());
    }

    public S homepage(String homepage) throws IOException {
        return with("homepage", homepage);
    }

    public S private_(boolean enabled) throws IOException {
        return with("private", enabled);
    }

    public S visibility(final Visibility visibility) throws IOException {
        return with("visibility", visibility.toString());
    }

    public S issues(boolean enabled) throws IOException {
        return with("has_issues", enabled);
    }

    public S projects(boolean enabled) throws IOException {
        return with("has_projects", enabled);
    }

    public S wiki(boolean enabled) throws IOException {
        return with("has_wiki", enabled);
    }

    public S downloads(boolean enabled) throws IOException {
        return with("has_downloads", enabled);
    }

    public S isTemplate(boolean enabled) throws IOException {
        return with("is_template", enabled);
    }

    public S forkDefaultBranchOnly(boolean enabled) throws IOException {
        this.defaultBranchOnly = enabled;
        return (S) this;
    }

    @Override
    public GHRepository done() throws IOException {
        if (defaultBranchOnly) {
            requester.with("default_branch_only", true);
        }
        return super.done();
    }

    S archive() throws IOException {
        return with("archived", true);
    }

    S name(String name) throws IOException {
        return with("name", name);
    }
}
