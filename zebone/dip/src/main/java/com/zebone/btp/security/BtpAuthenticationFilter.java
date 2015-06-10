package com.zebone.btp.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于URL的认证过滤器。继承了shiro框架的PassThruAuthenticationFilter类，并重写了preHandle方法。
 * 以前此方法的匹配原则是“首次路径匹配成功就算成功”，重写后改成了全部成功后才算成功。
 * @author 宋俊杰
 *
 */
public class BtpAuthenticationFilter extends PassThruAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(PathMatchingFilter.class);

	/**
	 * Implementation that handles path-matching behavior before a request is
	 * evaluated. If the path matches and the filter
	 * {@link #isEnabled(javax.servlet.ServletRequest, javax.servlet.ServletResponse, String, Object)
	 * isEnabled} for that path/config, the request will be allowed through via
	 * the result from
	 * {@link #onPreHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Object)
	 * onPreHandle}. If the path does not match or the filter is not enabled for
	 * that path, this filter will allow passthrough immediately to allow the
	 * {@code FilterChain} to continue executing.
	 * <p/>
	 * In order to retain path-matching functionality, subclasses should not
	 * override this method if at all possible, and instead override
	 * {@link #onPreHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Object)
	 * onPreHandle} instead.
	 * 
	 * @param request
	 *            the incoming ServletRequest
	 * @param response
	 *            the outgoing ServletResponse
	 * @return {@code true} if the filter chain is allowed to continue to
	 *         execute, {@code false} if a subclass has handled the request
	 *         explicitly.
	 * @throws Exception
	 *             if an error occurs
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		if (this.appliedPaths == null || this.appliedPaths.isEmpty()) {
			if (log.isTraceEnabled()) {
				log.trace("appliedPaths property is null or empty.  This Filter will passthrough immediately.");
			}
			return true;
		}

		for (String path : this.appliedPaths.keySet()) {
			// If the path does match, then pass on to the subclass
			// implementation for specific checks
			// (first match 'wins'):
			if (pathsMatch(path, request)) {
				log.trace("Current requestURI matches pattern '{}'.  Determining filter chain execution...", path);
				Object config = this.appliedPaths.get(path);
				if(!isFilterChainContinued(request, response, path, config)){
					return false;
				}				
			}
		}

		// no path matched, allow the request to go through:
		return true;
	}
	
	/**
     * Simple method to abstract out logic from the preHandle implementation - it was getting a bit unruly.
     *
     * @since 1.2
     */
    @SuppressWarnings({"JavaDoc"})
    private boolean isFilterChainContinued(ServletRequest request, ServletResponse response,
                                           String path, Object pathConfig) throws Exception {

        if (isEnabled(request, response, path, pathConfig)) { //isEnabled check added in 1.2
            if (log.isTraceEnabled()) {
                log.trace("Filter '{}' is enabled for the current request under path '{}' with config [{}].  " +
                        "Delegating to subclass implementation for 'onPreHandle' check.",
                        new Object[]{getName(), path, pathConfig});
            }
            //The filter is enabled for this specific request, so delegate to subclass implementations
            //so they can decide if the request should continue through the chain or not:
            return onPreHandle(request, response, pathConfig);
        }

        if (log.isTraceEnabled()) {
            log.trace("Filter '{}' is disabled for the current request under path '{}' with config [{}].  " +
                    "The next element in the FilterChain will be called immediately.",
                    new Object[]{getName(), path, pathConfig});
        }
        //This filter is disabled for this specific request,
        //return 'true' immediately to indicate that the filter will not process the request
        //and let the request/response to continue through the filter chain:
        return true;
    }
}
