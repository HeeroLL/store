package com.zebone.btp.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.service.RoleService;
import com.zebone.btp.app.role.vo.Role;

/**
 * Realms在Shiro和应用程序的安全数据之间担当“桥梁”或“连接器”。 它实际上与安全相关的数据(如用来执行身份验证（登录）及
 * 授权（访问控制）的用户帐户)交互时，Shiro从程序配置的Realm 中寻找身份验证或者授权的数据。
 * 
 * @author 宋俊杰
 * 
 */
public class ShiroDataBaseRealm extends AuthorizingRealm {
	private static final Log log = LogFactory.getLog(ShiroDataBaseRealm.class);
	@Resource(name = "personnelService")
	private PersonnelService personnelService = null;
	@Resource(name = "mhoService")
	private MhoService mhoService = null;
	@Resource
	private RoleService roleService = null;
	

	/**
	 * 当用户进行访问链接时的授权方法
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}

		UserDetails user = (UserDetails) principals.fromRealm(getName())
				.iterator().next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Role> roleList = user.getRoleList();
		for (Role role : roleList) {
			info.addRole(role.getRoleId());
		}
		return info;
	}

	/**
	 * 用户登录的认证方法。根据用登录凭证，返回登录人认证信息。
	 * 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String username = usernamePasswordToken.getUsername();

		if (username == null) {
			throw new AccountException("用户名不能为空");
		}
		Personnel personnel = null;
		try {
			personnel = this.personnelService.getPersonnelByLoginName(username);
		} catch (Exception e) {
			log.error("", e);
		}
		if (personnel == null) {
			throw new UnknownAccountException("用户不存在");
		}
		String personnelId = personnel.getPersonnelId();
		List<PersonnelMhoR> personnelMhoList = this.personnelService.getPersonnelMhoRInfo(personnelId);
		List<Role> roleList = this.roleService.getPersonnelRolesById(personnelId);
		List<Mho> mhoList = this.mhoService.getMhoByPersonnelId(personnelId);
		UserDetails user = new UserDetails();
		user.setPersonnelId(personnel.getPersonnelId());
		user.setLoginName(personnel.getLoginName());
		user.setPassword(personnel.getPassword());
		user.setFullname(personnel.getFullname());
		user.setPersonnelMhoList(personnelMhoList);
		user.setRoleList(roleList);
		user.setMhoList(mhoList);
		user.setSkin(personnel.getSkin());
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}
}
