select avg(greatest((hai11.duration_ + hai12.duration_+ hai13.duration_)
				   ,(hai21.duration_ + hai22.duration_+ hai23.duration_)
				   ,(hai31.duration_ + hai32.duration_+ hai33.duration_))/1000) as avg_duration
from dict_order ord
inner join act_hi_procinst hpi on hpi.business_key_ = ord.code
inner join act_hi_actinst hai11 on hai11.proc_inst_id_ = hpi.id_
							 and hai11.act_type_ = 'userTask'
							 and hai11.act_id_ = 'makeBlankForKP01LTask'
inner join act_hi_actinst hai12 on hai12.proc_inst_id_ = hpi.id_
							 and hai12.act_type_ = 'userTask'
							 and hai12.act_id_ = 'drillBlankForKP01LTask'
inner join act_hi_actinst hai13 on hai13.proc_inst_id_ = hpi.id_
							 and hai13.act_type_ = 'userTask'
							 and hai13.act_id_ = 'bendBlankForKP01LTask'
inner join act_hi_actinst hai21 on hai21.proc_inst_id_ = hpi.id_
							 and hai21.act_type_ = 'userTask'
							 and hai21.act_id_ = 'makeBlankForKP01PTask'
inner join act_hi_actinst hai22 on hai22.proc_inst_id_ = hpi.id_
							 and hai22.act_type_ = 'userTask'
							 and hai22.act_id_ = 'drillBlankForKP01PTask'
inner join act_hi_actinst hai23 on hai23.proc_inst_id_ = hpi.id_
							 and hai23.act_type_ = 'userTask'
							 and hai23.act_id_ = 'bendBlankForKP01PTask'
inner join act_hi_actinst hai31 on hai31.proc_inst_id_ = hpi.id_
							 and hai31.act_type_ = 'userTask'
							 and hai31.act_id_ = 'makeBlankForPK1Task'
inner join act_hi_actinst hai32 on hai32.proc_inst_id_ = hpi.id_
							 and hai32.act_type_ = 'userTask'
							 and hai32.act_id_ = 'drillBlankForPK1Task'
inner join act_hi_actinst hai33 on hai33.proc_inst_id_ = hpi.id_
							 and hai33.act_type_ = 'userTask'
							 and hai33.act_id_ = 'makeSpacerTask'
where ord.code = '2023-02-14-33'