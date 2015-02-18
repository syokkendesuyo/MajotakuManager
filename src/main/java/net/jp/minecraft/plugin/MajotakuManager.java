package net.jp.minecraft.plugin;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 魔女宅用TabListプラグイン
 * MajotakuManager
 * @author syokkendesuyo
 */

public class MajotakuManager extends JavaPlugin implements Listener {

	/**
     * プラグインが有効になったときに呼び出されるメソッド
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

	//額縁の設置をキャンセル
	//もしmajotaku.visitorならキャンセル
	@EventHandler
	public void onHangingPlaceEvent(HangingPlaceEvent event) {
		//もし左クリック先が額縁なら
		if (event.getEntity() instanceof ItemFrame) {
			//設置者を取得
			Player player =(Player)event.getPlayer();
			if(event.getPlayer().hasPermission("majotaku.*")||player.isOp()){
				event.setCancelled(false);
			}
			else if(event.getPlayer().hasPermission("majotaku.visitor")){
				event.setCancelled(true);
			}
		}
	}

	//額縁の中身を外そうとしたときのイベント
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof ItemFrame) {
			if(event.getDamager() instanceof Player){
				Player player =(Player)event.getDamager();
				if(player.hasPermission("majotaku.*")||player.isOp()){
					event.setCancelled(false);
				}
				else if(player.getPlayer().hasPermission("majotaku.visitor")){
					event.setCancelled(true);
				}
			}

        }
	}

	//額縁の中身をクルクルしたときのイベント
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof ItemFrame) {
			Player player =(Player)event.getPlayer();
			if(player.hasPermission("majotaku.*")||player.isOp()){
				event.setCancelled(false);
			}
			else if(player.hasPermission("majotaku.visitor")){
				event.setCancelled(true);
			}
        }
	}

	//額縁を破壊するときのイベント
	@EventHandler
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) {
		if (event.getEntity() instanceof ItemFrame) {
			Player player =(Player)event.getRemover();
			if(player.hasPermission("majotaku.*")||player.isOp()){
				event.setCancelled(false);
			}
			else if(player.hasPermission("majotaku.visitor")){
				event.setCancelled(true);
			}
		}
	}

	//破壊イベント
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event){
		if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
			event.setCancelled(false);
		}
		else if(event.getPlayer().hasPermission("majotaku.visitor")){
			event.setCancelled(true);
		}
	}

	//殴り始めをキャンセル
	@EventHandler
	public void onBlockDamageEvent(BlockDamageEvent event){
		if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
			event.setCancelled(false);
		}
		else if(event.getPlayer().hasPermission("majotaku.visitor")){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR ) {
			if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
				event.setCancelled(false);
			}
			else if(event.getPlayer().hasPermission("majotaku.visitor")){
				event.setCancelled(true);
			}
		}
	}

	//設置イベント
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event){
		if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
			event.setCancelled(false);
		}
		else if(event.getPlayer().hasPermission("majotaku.visitor")){
			event.setCancelled(true);
		}
	}

	//バケツのイベント
	@EventHandler
	public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
		if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
			event.setCancelled(false);
		}
		else if(event.getPlayer().hasPermission("majotaku.visitor")){
			event.setCancelled(true);
		}
	}

	//バケツのイベント
	@EventHandler
	public void onPlayerBucketFillEvent(PlayerBucketFillEvent event){
		if(event.getPlayer().hasPermission("majotaku.*")||event.getPlayer().isOp()){
			event.setCancelled(false);
		}
		else if(event.getPlayer().hasPermission("majotaku.visitor")){
			event.setCancelled(true);
		}
	}

	//耕地が戻ったりするイベントをキャンセル
	@EventHandler
	public void onBlockFadeEvent(BlockFadeEvent event){
		event.setCancelled(true);
	}
}