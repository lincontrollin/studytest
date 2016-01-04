<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>编辑资料</title>

<jsp:include page="/WEB-INF/jsp/common/top2013.jsp"></jsp:include>

<link href="http://images.zastatic.com/zhenai3/zhenai2012/css/statecos/edit_infos.css" rel="stylesheet" type="text/css">
<%--<link href="http://images.zastatic.com/zhenai3/zhenai2012/css/statecos/public.css" rel="stylesheet" type="text/css">
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/jquery-1.8.3.min.js"></script>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/LAB.min.js"></script>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/statecos/html5.js"></script>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/core/fixCore.js"></script>--%>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/artDialog/jquery.artDialog.js?skin=default"></script>
<link href="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/artDialog/skins/default.css?4.1.6" rel="stylesheet" type="text/css"/>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/artDialog/plugins/iframeTools.js"></script>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/widget/za_dialog.js?v=2013071"></script>
<!--[if IE]>
<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/statecos/html5.js"></script>
<![endif]-->
</head>
<body class="statecos">
<jsp:include page="/WEB-INF/jsp/common/head2013.jsp"></jsp:include>
<div class="frame_960 statecos_wrap clearfix">
    <jsp:include page="/homeleft.do"></jsp:include> 
    <article class="side_r">
    	<section class="profile_basic clearfix">
			<div class="breastpin"></div>
			
			<div class="profile_basic_info">
				<div class="profile_basic_top">
					<strong class="nickname">${nickName}</strong>
					<div class="integrity">
						<span>资料完整度</span>
						<div class="meter">
							<span class="proportion" style="width:15%;" id="infoper">
								<span>15%<i></i></span>
							</span>
						</div>
					</div>
				</div>
				<p>ID：${userinfo.memberID}</p>
				<p>${showInfoMap.age}岁　|　${showInfoMap.Constellation}　|　${showInfoMap.marry}</p>
				<p>${fn:replace(showInfoMap.workcity,'中国','')}|　身高${showInfoMap.height}　|　${showInfoMap.educate}</p>
				<div class="profile_basic_action"><a href="/member/showme.do" class="profile_preview_link"><i class="icon_preview"></i>预览主页</a><a href="###" class="profile_edit_link" data-layer=".modif_way"><i class="icon_edit"></i>我要修改</a></div>
			</div>
		</section>
		<section class="edit_infos">
			<ul class="tab_bar clearfix">
				<li class="current j_tab_trigger"><a href="javascript:void(0);">注册信息</a></li>
				<li class="j_tab_trigger"><a href="javascript:void(0);">基本资料</a></li>
				<li class="j_tab_trigger"><a href="javascript:void(0);">择偶条件</a></li>
			</ul>
			<ul class="tab_content">
            	<!--  注册信息 {{ -->
            	<form id="baseinfoForm">
				<li class="j_tab_body" id="registerInfo">
				
					<div class="form_row">
						<label class="form_label">　昵称：</label>
						<input type="text" class="form_input" value="${nickName}" name="nickName" id="nickName" maxlength="16">
						<c:if test="${! empty nickNameUncheck}">
						<div class="form_tips"><i class="icon_warning"></i>审核中...</div>
						</c:if>
					</div>
					<div class="form_row">
						<label class="form_label">　手机：</label>
						<input type="text" class="form_input" disabled="disabled" value="${phone}" id="mobile">
						<div class="form_tips"><i class="icon_warning"></i>免费获得网站的征婚进展提醒短信</div>
						<a href="javascript:void(0);" class="modif_mobile_link" data-layer=".modif_mobile">${phonevalid==1?'修改':'验证'}</a>
					</div>
					<div class="form_row">
						<label class="form_label">　邮箱：</label>
						<input type="text" class="form_input"  disabled="disabled" value="${email}" id="email">
						<div class="form_tips"><i class="icon_warning"></i>填写常用的邮箱地址，提高账户安全</div><a href="javascript:void(0);" class="modif_email_link" data-layer=".modif_email">修改</a>
					</div>
					<div class="form_row">
						<label class="form_label" >工作地：</label>
						<input type="text" class="form_input" id="myworkprovince_label" >
						<span class="form_dash">　</span>
						<input type="text" class="form_input" id="myworkcity_label">
						<input type="hidden" id="myworkProvince" name="areaForm.workProvince"/>
						<input type="hidden" id="myworkCity" name="areaForm.workCity" data="${userinfo.workCity}"/>
					</div>
					<div class="form_row" data="${userinfo.salary}">
						<label class="form_label">月收入：</label>
					</div>
					<div class="form_row birthland_row">
						<label class="form_label">　籍贯：</label>
						<input type="text" class="form_input" id="myhometownProvince_label" title="请选择">
						<span class="form_dash">　</span>
						<input type="text" class="form_input" id="myhometownCity_label" title="请选择">
						<input type="hidden" id="myhometownProvince" name="areaForm.hometownProvince"/>
						<input type="hidden" id="myhometownCity" name="areaForm.hometownCity" data="${userinfo.hometown}"/>
						
					</div>
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="savebaseinfobtn">保存并继续</a>
					</div>
				
				</li>
				</form>
            	<!--  注册信息 }} -->
            	<!--  基本资料 {{ -->
				<li class="j_tab_body" style="display:none;">
					<ul class="mtab_bar clearfix" id="infosummary">
						<li class="j_tab_trigger_m current"><a href="javascript:void(0);">经济情况</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">婚恋情况</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">家庭情况</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">生活习惯</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">自我描述</a></li>
					</ul>
					<ul class="mtab_content">
                    	<!--  经济情况 {{ -->
                    	<form>
						<li class="income_info j_tab_body_m" id="economyLi">
							<div class="form_row"  data="${userinfo.corpType}">
								<label class="form_label">单位类别：</label>
							</div>
							<div class="form_row"  data="${userinfo.occupation}">
								<label class="form_label">职业类别：</label>
							</div>
							<div class="form_row"  data="${mempri.isBuyCar}">
								<label class="form_label">是否购车：</label>
								
							</div>
							<div class="form_row"  data="${mempri.carPlan}" style="display:none;">
								<label class="form_label">购车计划：</label>
								
							</div>
							<div class="form_row" data="${userinfo.house}">
								<label class="form_label">是否购房：</label>
								
							</div>
							<div class="form_row"  data="${mempri.personalSavings}">
								<label class="form_label">有无存款：</label>
								
							</div>
						</li>
						</form>
            			<!--  经济情况 }} -->
                    	<!--  婚恋情况 {{ -->
                    	<form>
						<li class="marriage_info j_tab_body_m" style="display:none;" id="loveAndMarrigeLi">
							<div class="form_row" data="${mempri.marriageHistory}">
								<label class="form_label">　　　谈过几次恋爱：</label>
							</div>
							<div class="form_row" data="${userinfo3.marryDate}">
								<label class="form_label">　　　期望何时结婚：</label>
							</div>
							<div class="form_row form_dating" data="${memberCheckboxStr[0]}">
								<label class="form_label">　　　期望约会场所：</label>
                                <div class="form_control">
                                </div>
							</div>
							<div class="form_row" data="${userinfo.wantChildren}">
								<label class="form_label">　　　是否想要小孩：</label>
							</div>
							<div class="form_row" data="${userinfo3.liveWithParentsInLaw}">
								<label class="form_label">婚后与对方父母同住：</label>
								</div>
						</li>
						</form>
            			<!--  婚恋情况 }} -->
						
                    	<!--  家庭情况 {{ -->
                    	<form>
						<li class="family_info j_tab_body_m" style="display:none;" id="familyLi">
							<div class="form_row" data="${userinfo3.parents}">
								<label class="form_label">　　　父母现状：</label>
								
							</div>
							<div class="form_row" data="${userinfo3.liveWithParents}">
								<label class="form_label">婚后与父母同住：</label>
								
							</div>
							
							<div class="form_row" data="${mempri.siblingsCount}">
								<label class="form_label">　　兄弟姐妹数：</label>
							</div>
							<div class="form_row" data="${memberCheckboxStr[1]}" style="display:none;">
								<label class="form_label">　兄弟姐妹情况：</label>
								<div class="form_control">
                                </div>
							</div>
							<div class="form_row" data="${userinfo.children}">
								<label class="form_label">　　　有无孩子：</label>
							</div>
						</li>
						</form>
            			<!--  家庭情况 }} -->
						
                    	<!--  生活习惯 {{ -->
                    	<form>
						<li class="living_habit j_tab_body_m" style="display:none;" id="liveCustomLi">
							<div class="form_row" data="${userinfo3.cuisine }">
								<label class="form_label">　　厨艺：</label>
							</div>
							<div class="form_row" data="${userinfo.smoking }">
								<label class="form_label">是否吸烟：</label>
							</div>
							<div class="form_row" data="${userinfo.drinking}">
								<label class="form_label">是否喝酒：</label>
							</div>
							<div class="form_row" data="${userinfo3.housework}">
								<label class="form_label">婚后家务：</label>
							</div>
						</li>
						</form>
            			<!--  生活习惯 }} -->
                    	<!--  自我描述 {{ -->
                    	<form>
                        <li class="self_description j_tab_body_m" style="display:none;" id="myDescription">
							<div class="form_row" data="${introCode}">
								<label class="form_label">征婚宣言：</label>
                                <div class="form_control">
                                	<textarea  name="IntroduceContent" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="简单介绍下你自己,描绘一下你理想中的另一半的样子,说说你对未来婚姻家庭的期望."  <c:if test="${introCode==0}"> disabled="disabled"</c:if> >${userinfo.introduceContent}</textarea>
                                   <div class="textarea_tips">
                                    <c:if test="${introCode!=0}">
                                    	<p class="limit_num">至少30字，您还可以输入<span class="j_remaining_num">1500</span>字。</p>
                                    </c:if>
                                    <c:if test="${introCode==0}">
                                    	<span class="check_tips" style="display:block">审核中...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${workCode}">
								<label class="form_label">工作详情：</label>
                                <div class="form_control">
                                	<textarea name="workAttitude" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="平常工作主要做什么？忙不忙，压力大吗？对目前的工作还满意吗？接下来有什么发展规划？" <c:if test="${workCode==0}">disabled="disabled"</c:if>>${userinfo3.workAttitude}</textarea>
                                    <div class="textarea_tips">
                                    <c:if test="${workCode!=0}">
                                    	<p class="limit_num">至少30字，您还可以输入<span class="j_remaining_num">1500</span>字。</p>
                                    </c:if>
                                    <c:if test="${workCode==0}">
                                    	<span class="check_tips" style="display:block">审核中...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${familyCode}">
								<label class="form_label">家庭情况：</label>
                                <div class="form_control">
                                	<textarea name="familySituation" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="说一说你的家庭，父母身体好吗？平常家庭关系和睦吗？兄弟姐妹都在做什么？你们关系如何？"
                                	<c:if test="${familyCode==0}">disabled="disabled"</c:if>>${userinfo3.familySituation}</textarea>
                                	<div class="textarea_tips">
	                                    <c:if test="${familyCode!=0}">
	                                    	<p class="limit_num">至少30字，您还可以输入<span class="j_remaining_num">1500</span>字。</p>
	                                    	</p>
	                                    </c:if>
	                                     <c:if test="${familyCode==0}">
	                                    	<span class="check_tips" style="display:block">审核中...</span>
	                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${interestCode}">
								<label class="form_label">业余爱好：</label>
                                <div class="form_control">
                                	<textarea name="interests" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="说说平常下班没事儿了，在你的城市，你去得最多的3个场所。"
                                	<c:if test="${interestCode==0}">disabled="disabled"</c:if>>${userinfo3.interests}</textarea>
                                    <div class="textarea_tips">
                                    <c:if test="${interestCode!=0}">
                                    	<p class="limit_num">至少30字，您还可以输入<span class="j_remaining_num">1500</span>字。</p>
                                    </c:if>
                                     <c:if test="${interestCode==0}">
                                    	<span class="check_tips" style="display:block">审核中...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
						</li>
						</form>
            			<!--  注册信息 }} -->
            			
					
					</ul>
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="savemyinfobtn">保存并继续</a>
					</div>

				</li>
            	<!--  基本资料 }} -->
				
            	<!--  择偶条件 {{ -->
            	<form id="objectForm">
				<li class="mate_criterion j_tab_body" style="display:none;" id="objectConditionLi">
				
					<div class="form_row" data="${objectinfo.age1}" data-exc="${objectinfo.age2}">
						<label class="form_label">年龄：</label>
						<span class="form_dash">至</span></div>
					<div class="form_row" data="${objectinfo.education}">
						<label class="form_label">学历：</label></div>
					<div class="form_row" data="${objectinfo.height1}" data-exc="${objectinfo.height2}">
						<label class="form_label">身高：</label>
						<span class="form_dash">至</span></div>
					<div class="form_row" data="${objectinfo.salary1}" data-exc="${objectinfo.salary2}" >
						<label class="form_label">收入：</label>
						<span class="form_dash">至</span>
					</div>
					<div class="form_row" >
						<label class="form_label" >工作地：</label><input type="text" class="form_input" id="workprovince_label"><span class="form_dash">　</span><input type="text" class="form_input" id="workcity_label">
						<input type="hidden" id="workProvince" name="areaForm.workProvince"/>
						<input type="hidden" id="workCity" name="areaForm.workCity" data="${objectinfo.workCity}"/>
					</div>
					<div class="form_row" >
						<label class="form_label">籍贯：</label><input type="text" class="form_input" id="hometownProvince_label" title="请选择"><span class="form_dash">　</span><input type="text" class="form_input" id="hometownCity_label">
						<input type="hidden" id="hometownProvince" name="areaForm.hometownProvince" title="请选择"/>
						<input type="hidden" id="hometownCity" name="areaForm.hometownCity" data="${objectinfo.hometown}"/>
					</div>
					<div class="form_row" data="${objectinfo.children}" >
						<label class="form_label">是否有小孩：</label></div>
					<div class="form_row" data="${objectinfo.isDrinking}"> 
						<label class="form_label">是否介意喝酒：</label></div>
					<div class="form_row" data="${objectinfo.isSmoking}">
						<label class="form_label">是否介意抽烟：</label></div>
					
				
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="objectFormBtn">保存并继续</a>
					</div>
				</li>
				</form>
            	<!--  择偶条件 }} -->
			</ul>
		</section>
    </article>
</div>
<%--<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/sea.js"></script>--%>
<jsp:include page="/WEB-INF/jsp/common/tail.jsp"></jsp:include>
<!-- 修改手机 浮层 {{ -->
<div class="floating_layer modif_mobile">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="关闭">×</a>
	<h3 class="mtitle">您的手机号已经通过验证！确认换新：</h3>
	<div class="floating_layer_main">
		<div class="form_row">
			<label>手机号：</label>
			<div class="form_control">
				<input type="text" class="form_input" value="<c:if test="${phonevalid!=1}">${phone}</c:if>" id="tel">
				<div class="form_err"></div>
			</div><a href="javascript:void(0);" class="btn_orange_S" id="getAuthCode">免费获取</a>
		</div>
		<div class="form_row">
			<label>验证码：</label>
			<div class="form_control">
				<input type="text" class="form_input" id="code">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>请输入短信中的4位数确认码!</div>
			</div>
		</div>
		<div class="form_row form_action">
			<label>　　　　</label><a href="javascript:void(0);" class="btn_blue_L" id="validateMobile">立即验证</a>
		</div>
	</div>
</div>
<!-- 修改手机 浮层 }} -->
<!-- 修改邮箱 浮层 {{ -->
<div class="floating_layer modif_email">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="关闭">×</a>
	<h3 class="mtitle">修改邮箱地址:</h3>
	<div class="floating_layer_main">
		<div class="form_row">
			<label>　邮箱地址：</label>
			<div class="form_control">
				<input type="text" class="form_input" id="emailText">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>请输入您要修改的邮箱地址</div>
			</div>
		</div>
		<div class="form_row">
			<label>珍爱网密码：</label>
			<div class="form_control">
				<input type="password" class="form_input" id="emailPassword">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>为保证信息安全，需进行密码验证</div>
			</div>
		</div>
		<div class="form_row form_action">
			<label>　　　　　　</label><a href="javascript:void(0);" class="btn_blue_L" id="validateEmail">立即验证</a>
		</div>
	</div>
</div>
<!-- 修改邮箱 浮层 }} -->

<!-- 修改途径 浮层 {{ -->
<div class="floating_layer modif_way">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="关闭">×</a>
	<h3 class="mtitle">修改途径:</h3>
	<div class="floating_layer_main">
		<p><strong>　　性别：</strong>请致电：4001-520-520</p>
		<p><strong>出生日期：</strong><a href="http://profile.zhenai.com/idcard/getMyIdInfo.jsps" target="_blank">认证身份证</a>或<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">上传身份证</a>，系统自动更改</p>
		<p><strong>婚姻状况：</strong><a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">上传户口本</a>或<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">婚育证明</a>，客服帮助修改</p>
		<p><strong>　　学历：</strong><a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">上传毕业证</a>或<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">学位证</a>，客服帮助修改</p>
		<p><strong>　　身高：</strong>发送体检证明至kefu@zhenai.com，</p>
		<p><strong>　　　　　</strong>注明ID和真实姓名，客服帮助修改</p>
	</div>
</div>
<!-- 修改途径 浮层 }} -->
<div id="goUp" class="statecos-up"><a class="gotop" href="###" title="返回顶部"></a><a class="feedback" href="###">意见反馈</a></div>

<script>
seajs.config({
	charset:'GBK',
	base: "http://images.zastatic.com/zhenai3/zhenai2012/js/statecos/"
});
seajs.use(["http://images.zastatic.com/zhenai3/zhenai2012/js/statecos/page_edit_info","http://images.zastatic.com/zhenai3/zhenai2012/js/statecos/edit_info"],function(){
	var showtag = parseInt('${showtag}');
	if(showtag>1&&showtag<4){
		$('.j_tab_trigger').eq(showtag-1).trigger('click');
	}
	var basetag = parseInt('${baseTag}');
	$('.j_tab_trigger_m').eq(basetag-1).trigger('click')
	
});
</script>
</body>
</html>