/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.web.authentication;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A holder of selected HTTP details related to a web authentication request.
 * 
 * @author Ben Alex
 */
public class WebAuthenticationDetails implements Serializable {
	// ~ Instance fields
	// ================================================================================================

	private final String remoteAddress;
	private final String sessionId;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Records the remote address and will also set the session Id if a session
	 * already exists (it won't create one).
	 * 
	 * @param request
	 *            that the authentication request was received from
	 */
	public WebAuthenticationDetails(HttpServletRequest request) {
		this.remoteAddress = request.getRemoteAddr();

		HttpSession session = request.getSession(false);
		this.sessionId = (session != null) ? session.getId() : null;

		doPopulateAdditionalInformation(request);
	}

	// ~ Methods
	// ========================================================================================================

	/**
	 * Provided so that subclasses can populate additional information.
	 * 
	 * @param request
	 *            that the authentication request was received from
	 */
	protected void doPopulateAdditionalInformation(HttpServletRequest request) {
	}

	public boolean equals(Object obj) {
		if (obj instanceof WebAuthenticationDetails) {
			WebAuthenticationDetails rhs = (WebAuthenticationDetails) obj;

			if ((remoteAddress == null) && (rhs.getRemoteAddress() != null)) {
				return false;
			}

			if ((remoteAddress != null) && (rhs.getRemoteAddress() == null)) {
				return false;
			}

			if (remoteAddress != null) {
				if (!remoteAddress.equals(rhs.getRemoteAddress())) {
					return false;
				}
			}

			if ((sessionId == null) && (rhs.getSessionId() != null)) {
				return false;
			}

			if ((sessionId != null) && (rhs.getSessionId() == null)) {
				return false;
			}

			if (sessionId != null) {
				if (!sessionId.equals(rhs.getSessionId())) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * Indicates the TCP/IP address the authentication request was received
	 * from.
	 * 
	 * @return the address
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * Indicates the <code>HttpSession</code> id the authentication request was
	 * received from.
	 * 
	 * @return the session ID
	 */
	public String getSessionId() {
		return sessionId;
	}

	public int hashCode() {
		int code = 7654;

		if (this.remoteAddress != null) {
			code = code * (this.remoteAddress.hashCode() % 7);
		}

		if (this.sessionId != null) {
			code = code * (this.sessionId.hashCode() % 7);
		}

		return code;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
		sb.append("SessionId: ").append(this.getSessionId());

		return sb.toString();
	}
}
