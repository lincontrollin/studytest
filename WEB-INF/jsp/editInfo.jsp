<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�༭����</title>

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
						<span>����������</span>
						<div class="meter">
							<span class="proportion" style="width:15%;" id="infoper">
								<span>15%<i></i></span>
							</span>
						</div>
					</div>
				</div>
				<p>ID��${userinfo.memberID}</p>
				<p>${showInfoMap.age}�ꡡ|��${showInfoMap.Constellation}��|��${showInfoMap.marry}</p>
				<p>${fn:replace(showInfoMap.workcity,'�й�','')}|�����${showInfoMap.height}��|��${showInfoMap.educate}</p>
				<div class="profile_basic_action"><a href="/member/showme.do" class="profile_preview_link"><i class="icon_preview"></i>Ԥ����ҳ</a><a href="###" class="profile_edit_link" data-layer=".modif_way"><i class="icon_edit"></i>��Ҫ�޸�</a></div>
			</div>
		</section>
		<section class="edit_infos">
			<ul class="tab_bar clearfix">
				<li class="current j_tab_trigger"><a href="javascript:void(0);">ע����Ϣ</a></li>
				<li class="j_tab_trigger"><a href="javascript:void(0);">��������</a></li>
				<li class="j_tab_trigger"><a href="javascript:void(0);">��ż����</a></li>
			</ul>
			<ul class="tab_content">
            	<!--  ע����Ϣ {{ -->
            	<form id="baseinfoForm">
				<li class="j_tab_body" id="registerInfo">
				
					<div class="form_row">
						<label class="form_label">���ǳƣ�</label>
						<input type="text" class="form_input" value="${nickName}" name="nickName" id="nickName" maxlength="16">
						<c:if test="${! empty nickNameUncheck}">
						<div class="form_tips"><i class="icon_warning"></i>�����...</div>
						</c:if>
					</div>
					<div class="form_row">
						<label class="form_label">���ֻ���</label>
						<input type="text" class="form_input" disabled="disabled" value="${phone}" id="mobile">
						<div class="form_tips"><i class="icon_warning"></i>��ѻ����վ�������չ���Ѷ���</div>
						<a href="javascript:void(0);" class="modif_mobile_link" data-layer=".modif_mobile">${phonevalid==1?'�޸�':'��֤'}</a>
					</div>
					<div class="form_row">
						<label class="form_label">�����䣺</label>
						<input type="text" class="form_input"  disabled="disabled" value="${email}" id="email">
						<div class="form_tips"><i class="icon_warning"></i>��д���õ������ַ������˻���ȫ</div><a href="javascript:void(0);" class="modif_email_link" data-layer=".modif_email">�޸�</a>
					</div>
					<div class="form_row">
						<label class="form_label" >�����أ�</label>
						<input type="text" class="form_input" id="myworkprovince_label" >
						<span class="form_dash">��</span>
						<input type="text" class="form_input" id="myworkcity_label">
						<input type="hidden" id="myworkProvince" name="areaForm.workProvince"/>
						<input type="hidden" id="myworkCity" name="areaForm.workCity" data="${userinfo.workCity}"/>
					</div>
					<div class="form_row" data="${userinfo.salary}">
						<label class="form_label">�����룺</label>
					</div>
					<div class="form_row birthland_row">
						<label class="form_label">�����᣺</label>
						<input type="text" class="form_input" id="myhometownProvince_label" title="��ѡ��">
						<span class="form_dash">��</span>
						<input type="text" class="form_input" id="myhometownCity_label" title="��ѡ��">
						<input type="hidden" id="myhometownProvince" name="areaForm.hometownProvince"/>
						<input type="hidden" id="myhometownCity" name="areaForm.hometownCity" data="${userinfo.hometown}"/>
						
					</div>
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="savebaseinfobtn">���沢����</a>
					</div>
				
				</li>
				</form>
            	<!--  ע����Ϣ }} -->
            	<!--  �������� {{ -->
				<li class="j_tab_body" style="display:none;">
					<ul class="mtab_bar clearfix" id="infosummary">
						<li class="j_tab_trigger_m current"><a href="javascript:void(0);">�������</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">�������</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">��ͥ���</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">����ϰ��</a></li>
						<li class="j_tab_trigger_m"><a href="javascript:void(0);">��������</a></li>
					</ul>
					<ul class="mtab_content">
                    	<!--  ������� {{ -->
                    	<form>
						<li class="income_info j_tab_body_m" id="economyLi">
							<div class="form_row"  data="${userinfo.corpType}">
								<label class="form_label">��λ���</label>
							</div>
							<div class="form_row"  data="${userinfo.occupation}">
								<label class="form_label">ְҵ���</label>
							</div>
							<div class="form_row"  data="${mempri.isBuyCar}">
								<label class="form_label">�Ƿ񹺳���</label>
								
							</div>
							<div class="form_row"  data="${mempri.carPlan}" style="display:none;">
								<label class="form_label">�����ƻ���</label>
								
							</div>
							<div class="form_row" data="${userinfo.house}">
								<label class="form_label">�Ƿ񹺷���</label>
								
							</div>
							<div class="form_row"  data="${mempri.personalSavings}">
								<label class="form_label">���޴�</label>
								
							</div>
						</li>
						</form>
            			<!--  ������� }} -->
                    	<!--  ������� {{ -->
                    	<form>
						<li class="marriage_info j_tab_body_m" style="display:none;" id="loveAndMarrigeLi">
							<div class="form_row" data="${mempri.marriageHistory}">
								<label class="form_label">������̸������������</label>
							</div>
							<div class="form_row" data="${userinfo3.marryDate}">
								<label class="form_label">������������ʱ��飺</label>
							</div>
							<div class="form_row form_dating" data="${memberCheckboxStr[0]}">
								<label class="form_label">����������Լ�᳡����</label>
                                <div class="form_control">
                                </div>
							</div>
							<div class="form_row" data="${userinfo.wantChildren}">
								<label class="form_label">�������Ƿ���ҪС����</label>
							</div>
							<div class="form_row" data="${userinfo3.liveWithParentsInLaw}">
								<label class="form_label">�����Է���ĸͬס��</label>
								</div>
						</li>
						</form>
            			<!--  ������� }} -->
						
                    	<!--  ��ͥ��� {{ -->
                    	<form>
						<li class="family_info j_tab_body_m" style="display:none;" id="familyLi">
							<div class="form_row" data="${userinfo3.parents}">
								<label class="form_label">��������ĸ��״��</label>
								
							</div>
							<div class="form_row" data="${userinfo3.liveWithParents}">
								<label class="form_label">����븸ĸͬס��</label>
								
							</div>
							
							<div class="form_row" data="${mempri.siblingsCount}">
								<label class="form_label">�����ֵܽ�������</label>
							</div>
							<div class="form_row" data="${memberCheckboxStr[1]}" style="display:none;">
								<label class="form_label">���ֵܽ��������</label>
								<div class="form_control">
                                </div>
							</div>
							<div class="form_row" data="${userinfo.children}">
								<label class="form_label">���������޺��ӣ�</label>
							</div>
						</li>
						</form>
            			<!--  ��ͥ��� }} -->
						
                    	<!--  ����ϰ�� {{ -->
                    	<form>
						<li class="living_habit j_tab_body_m" style="display:none;" id="liveCustomLi">
							<div class="form_row" data="${userinfo3.cuisine }">
								<label class="form_label">�������գ�</label>
							</div>
							<div class="form_row" data="${userinfo.smoking }">
								<label class="form_label">�Ƿ����̣�</label>
							</div>
							<div class="form_row" data="${userinfo.drinking}">
								<label class="form_label">�Ƿ�Ⱦƣ�</label>
							</div>
							<div class="form_row" data="${userinfo3.housework}">
								<label class="form_label">������</label>
							</div>
						</li>
						</form>
            			<!--  ����ϰ�� }} -->
                    	<!--  �������� {{ -->
                    	<form>
                        <li class="self_description j_tab_body_m" style="display:none;" id="myDescription">
							<div class="form_row" data="${introCode}">
								<label class="form_label">�������ԣ�</label>
                                <div class="form_control">
                                	<textarea  name="IntroduceContent" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="�򵥽��������Լ�,���һ���������е���һ�������,˵˵���δ��������ͥ������."  <c:if test="${introCode==0}"> disabled="disabled"</c:if> >${userinfo.introduceContent}</textarea>
                                   <div class="textarea_tips">
                                    <c:if test="${introCode!=0}">
                                    	<p class="limit_num">����30�֣�������������<span class="j_remaining_num">1500</span>�֡�</p>
                                    </c:if>
                                    <c:if test="${introCode==0}">
                                    	<span class="check_tips" style="display:block">�����...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${workCode}">
								<label class="form_label">�������飺</label>
                                <div class="form_control">
                                	<textarea name="workAttitude" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="ƽ��������Ҫ��ʲô��æ��æ��ѹ�����𣿶�Ŀǰ�Ĺ����������𣿽�������ʲô��չ�滮��" <c:if test="${workCode==0}">disabled="disabled"</c:if>>${userinfo3.workAttitude}</textarea>
                                    <div class="textarea_tips">
                                    <c:if test="${workCode!=0}">
                                    	<p class="limit_num">����30�֣�������������<span class="j_remaining_num">1500</span>�֡�</p>
                                    </c:if>
                                    <c:if test="${workCode==0}">
                                    	<span class="check_tips" style="display:block">�����...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${familyCode}">
								<label class="form_label">��ͥ�����</label>
                                <div class="form_control">
                                	<textarea name="familySituation" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="˵һ˵��ļ�ͥ����ĸ�������ƽ����ͥ��ϵ�������ֵܽ��ö�����ʲô�����ǹ�ϵ��Σ�"
                                	<c:if test="${familyCode==0}">disabled="disabled"</c:if>>${userinfo3.familySituation}</textarea>
                                	<div class="textarea_tips">
	                                    <c:if test="${familyCode!=0}">
	                                    	<p class="limit_num">����30�֣�������������<span class="j_remaining_num">1500</span>�֡�</p>
	                                    	</p>
	                                    </c:if>
	                                     <c:if test="${familyCode==0}">
	                                    	<span class="check_tips" style="display:block">�����...</span>
	                                    </c:if>
                                    </div>
                                </div>
							</div>
							<div class="form_row" data="${interestCode}">
								<label class="form_label">ҵ�మ�ã�</label>
                                <div class="form_control">
                                	<textarea name="interests" class="form_textarea" data-upper-limit="1500" data-lower-limit="30" placeholder="˵˵ƽ���°�û�¶��ˣ�����ĳ��У���ȥ������3��������"
                                	<c:if test="${interestCode==0}">disabled="disabled"</c:if>>${userinfo3.interests}</textarea>
                                    <div class="textarea_tips">
                                    <c:if test="${interestCode!=0}">
                                    	<p class="limit_num">����30�֣�������������<span class="j_remaining_num">1500</span>�֡�</p>
                                    </c:if>
                                     <c:if test="${interestCode==0}">
                                    	<span class="check_tips" style="display:block">�����...</span>
                                    </c:if>
                                    </div>
                                </div>
							</div>
						</li>
						</form>
            			<!--  ע����Ϣ }} -->
            			
					
					</ul>
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="savemyinfobtn">���沢����</a>
					</div>

				</li>
            	<!--  �������� }} -->
				
            	<!--  ��ż���� {{ -->
            	<form id="objectForm">
				<li class="mate_criterion j_tab_body" style="display:none;" id="objectConditionLi">
				
					<div class="form_row" data="${objectinfo.age1}" data-exc="${objectinfo.age2}">
						<label class="form_label">���䣺</label>
						<span class="form_dash">��</span></div>
					<div class="form_row" data="${objectinfo.education}">
						<label class="form_label">ѧ����</label></div>
					<div class="form_row" data="${objectinfo.height1}" data-exc="${objectinfo.height2}">
						<label class="form_label">��ߣ�</label>
						<span class="form_dash">��</span></div>
					<div class="form_row" data="${objectinfo.salary1}" data-exc="${objectinfo.salary2}" >
						<label class="form_label">���룺</label>
						<span class="form_dash">��</span>
					</div>
					<div class="form_row" >
						<label class="form_label" >�����أ�</label><input type="text" class="form_input" id="workprovince_label"><span class="form_dash">��</span><input type="text" class="form_input" id="workcity_label">
						<input type="hidden" id="workProvince" name="areaForm.workProvince"/>
						<input type="hidden" id="workCity" name="areaForm.workCity" data="${objectinfo.workCity}"/>
					</div>
					<div class="form_row" >
						<label class="form_label">���᣺</label><input type="text" class="form_input" id="hometownProvince_label" title="��ѡ��"><span class="form_dash">��</span><input type="text" class="form_input" id="hometownCity_label">
						<input type="hidden" id="hometownProvince" name="areaForm.hometownProvince" title="��ѡ��"/>
						<input type="hidden" id="hometownCity" name="areaForm.hometownCity" data="${objectinfo.hometown}"/>
					</div>
					<div class="form_row" data="${objectinfo.children}" >
						<label class="form_label">�Ƿ���С����</label></div>
					<div class="form_row" data="${objectinfo.isDrinking}"> 
						<label class="form_label">�Ƿ����Ⱦƣ�</label></div>
					<div class="form_row" data="${objectinfo.isSmoking}">
						<label class="form_label">�Ƿ������̣�</label></div>
					
				
					<div class="form_row form_submit_action">
						<a href="javascript:void(0);" class="btn_blue_L" id="objectFormBtn">���沢����</a>
					</div>
				</li>
				</form>
            	<!--  ��ż���� }} -->
			</ul>
		</section>
    </article>
</div>
<%--<script src="http://images.zastatic.com/zhenai3/zhenai2012/js/lib/sea.js"></script>--%>
<jsp:include page="/WEB-INF/jsp/common/tail.jsp"></jsp:include>
<!-- �޸��ֻ� ���� {{ -->
<div class="floating_layer modif_mobile">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="�ر�">��</a>
	<h3 class="mtitle">�����ֻ����Ѿ�ͨ����֤��ȷ�ϻ��£�</h3>
	<div class="floating_layer_main">
		<div class="form_row">
			<label>�ֻ��ţ�</label>
			<div class="form_control">
				<input type="text" class="form_input" value="<c:if test="${phonevalid!=1}">${phone}</c:if>" id="tel">
				<div class="form_err"></div>
			</div><a href="javascript:void(0);" class="btn_orange_S" id="getAuthCode">��ѻ�ȡ</a>
		</div>
		<div class="form_row">
			<label>��֤�룺</label>
			<div class="form_control">
				<input type="text" class="form_input" id="code">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>����������е�4λ��ȷ����!</div>
			</div>
		</div>
		<div class="form_row form_action">
			<label>��������</label><a href="javascript:void(0);" class="btn_blue_L" id="validateMobile">������֤</a>
		</div>
	</div>
</div>
<!-- �޸��ֻ� ���� }} -->
<!-- �޸����� ���� {{ -->
<div class="floating_layer modif_email">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="�ر�">��</a>
	<h3 class="mtitle">�޸������ַ:</h3>
	<div class="floating_layer_main">
		<div class="form_row">
			<label>�������ַ��</label>
			<div class="form_control">
				<input type="text" class="form_input" id="emailText">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>��������Ҫ�޸ĵ������ַ</div>
			</div>
		</div>
		<div class="form_row">
			<label>�䰮�����룺</label>
			<div class="form_control">
				<input type="password" class="form_input" id="emailPassword">
				<div class="form_err" style="display:none;"><i class="icon_warning"></i>Ϊ��֤��Ϣ��ȫ�������������֤</div>
			</div>
		</div>
		<div class="form_row form_action">
			<label>������������</label><a href="javascript:void(0);" class="btn_blue_L" id="validateEmail">������֤</a>
		</div>
	</div>
</div>
<!-- �޸����� ���� }} -->

<!-- �޸�;�� ���� {{ -->
<div class="floating_layer modif_way">
	<i class="floating_layer_arrow"></i>
	<a href="javascript:void(0);" class="floating_layer_close" title="�ر�">��</a>
	<h3 class="mtitle">�޸�;��:</h3>
	<div class="floating_layer_main">
		<p><strong>�����Ա�</strong>���µ磺4001-520-520</p>
		<p><strong>�������ڣ�</strong><a href="http://profile.zhenai.com/idcard/getMyIdInfo.jsps" target="_blank">��֤���֤</a>��<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">�ϴ����֤</a>��ϵͳ�Զ�����</p>
		<p><strong>����״����</strong><a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">�ϴ����ڱ�</a>��<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">����֤��</a>���ͷ������޸�</p>
		<p><strong>����ѧ����</strong><a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">�ϴ���ҵ֤</a>��<a href="http://profile.zhenai.com/credit/convinceindex.jsps" target="_blank">ѧλ֤</a>���ͷ������޸�</p>
		<p><strong>������ߣ�</strong>�������֤����kefu@zhenai.com��</p>
		<p><strong>����������</strong>ע��ID����ʵ�������ͷ������޸�</p>
	</div>
</div>
<!-- �޸�;�� ���� }} -->
<div id="goUp" class="statecos-up"><a class="gotop" href="###" title="���ض���"></a><a class="feedback" href="###">�������</a></div>

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